import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static ArrayList<int[]>[][] switchs;
    static Queue<int[]> q = new LinkedList<>();
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[][] lightOn;
    static boolean[][] visited;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        switchs = new ArrayList[n + 1][n + 1];
        lightOn = new boolean[n + 1][n + 1];
        visited = new boolean[n + 1][n + 1];
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++) switchs[i][j] = new ArrayList<>();
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            switchs[x][y].add(new int[]{a, b});
        }

        lightOn[1][1] = true;
        visited[1][1] = true;
        q.add(new int[]{1, 1});
        int cnt = 1;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            for(int[] temp : switchs[r][c]){
                int nr = temp[0];
                int nc = temp[1];
                if(!lightOn[nr][nc]){
                    lightOn[nr][nc] = true;
                    cnt++;
                    boolean flag = false;
                    for(int d=0; d<4; d++){
                        int nnr = nr + dr[d];
                        int nnc = nc + dc[d];
                        // 새로 불이 켜진 방이 이전에 방문한 방과 인접해 있으면
                        if(nnr >= 1 && nnr <= n && nnc >= 1 && nnc <= n && visited[nnr][nnc]){
                            flag = true;
                            break;
                        }
                    }
                    // 새로 켜진 방을 방문처리, 큐에 삽입
                    if(flag && !visited[nr][nc]){
                        visited[nr][nc] = true;
                        q.add(new int[]{nr, nc});
                    }
                }
            }
            // 현재 방에서 인접한 방 중에 불이 켜져있는 방을 방문처리 및 큐에 삽입
            for(int d=0; d<4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr < 1 || nr > n || nc < 1 || nc > n) continue;
                if(lightOn[nr][nc] && !visited[nr][nc]){
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                }
            }
        }
        System.out.print(cnt);
    }
}