import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {0, 0, -1, 0, 1, 0};
    static int[] dc = {0, 0, 0, 1, 0, -1};
    static int[] dz = {-1, 1, 0, 0, 0, 0};
    static boolean[][][] visited;
    static char[][][] matrix;
    static int L, R, C;
    static int[] start, end;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        while(true){
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());   // 층 수
            if(L== 0) break;
            R = Integer.parseInt(st.nextToken());   // 행
            C = Integer.parseInt(st.nextToken());   // 열
            matrix = new char[L][R][C];      // [층][행][열]
            visited = new boolean[L][R][C];
            start = new int[3];
            end = new int[3];
            for(int i=0; i<L; i++){
                for(int j=0; j<R; j++){
                    String line = br.readLine();
                    for(int p=0; p<C; p++){
                        matrix[i][j][p] = line.charAt(p);
                        if(matrix[i][j][p] == 'S'){
                            start[0] = i; start[1] = j; start[2] = p;
                        }
                        if(matrix[i][j][p] == 'E'){
                            end[0] = i; end[1] = j; end[2] = p;
                        }
                    }
                }
                br.readLine();
            }
            bfs(start[0], start[1], start[2]);
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }

    static void bfs(int z, int r, int c){
        Queue<Element> q = new LinkedList<>();
        q.add(new Element(z, r, c, 0));
        visited[z][r][c] = true;
        while(!q.isEmpty()){
            Element cur = q.poll();
            for(int d=0; d<6; d++){
                int nz = cur.z + dz[d];
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if(nz == end[0] && nr == end[1] && nc == end[2]){
                    sb.append("Escaped in ").append(cur.time + 1).append(" minute(s).").append("\n");
                    return;
                }
                if(nz < 0 || nz >= L || nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nz][nr][nc]) continue;
                if(matrix[nz][nr][nc] == '#') continue;
                visited[nz][nr][nc] = true;
                q.add(new Element(nz, nr, nc, cur.time + 1));
            }
        }
        sb.append("Trapped!").append("\n");
    }

    static class Element{
        int z; int r; int c; int time;
        Element(int z, int r, int c, int time){
            this.z = z; this.r = r; this.c = c; this.time = time;
        }
    }
}