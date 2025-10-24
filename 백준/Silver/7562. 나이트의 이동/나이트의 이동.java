import java.io.*;
import java.util.*;

public class Main {
    static int t;
    static boolean[][] visited;
    static int[] dr = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dc = {1, 2, 2, 1, -1, -2, -2, -1};
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            int l = 0; int[] start = new int[2]; int[] end = new int[2];
            l = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            start[0] = Integer.parseInt(st.nextToken());
            start[1] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            end[0] = Integer.parseInt(st.nextToken());
            end[1] = Integer.parseInt(st.nextToken());
            if(start[0] == end[0] && start[1] == end[1]){
                sb.append(0).append("\n");
                continue;
            }
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{start[0], start[1], 0});
            visited = new boolean[l + 1][l + 1];
            visited[start[0]][start[1]] = true;
            boolean flag = true;
            outer:
            while(!q.isEmpty()){
                int[] cur = q.poll();
                for(int d=0; d<8; d++){
                    int nr = cur[0] + dr[d];
                    int nc = cur[1] + dc[d];
                    if(nr == end[0] && nc == end[1]){
                        sb.append(cur[2] + 1).append("\n");
                        flag = false;
                        break outer;
                    }
                    if(nr < 0 || nr >= l || nc < 0 || nc >= l || visited[nr][nc]) continue;
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc, cur[2] + 1});
                }
            }
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }
}