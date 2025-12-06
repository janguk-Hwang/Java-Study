import java.io.*;
import java.util.*;

// 멀티소스 bfs로 불을 확산시키고 어떤 칸에 대해서 몇 초 후에 불이 확산되는지 기록해놓는다.
// 지훈이를 bfs로 이동시킨다. 불보다 먼저 도착하는 칸이면 이동한다. 이렇게 이동하면서 가장자리에 도착하면 그 때의 시간을 출력한다.
// 불가능하면 IMPOSSIBLE 출력.
public class Main {
    static Queue<int[]> fireQ = new LinkedList<>();
    static Queue<int[]> jihoonQ = new LinkedList<>();
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int r, c;
    static char[][] matrix;
    static int[][] fire, jihoon;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        matrix = new char[r][c];
        fire = new int[r][c];
        jihoon = new int[r][c];
        for(int i=0; i<r; i++){
            String line = br.readLine();
            for(int j=0; j<c; j++) matrix[i][j] = line.charAt(j);
        }
        for(int i=0; i<r; i++){
            Arrays.fill(fire[i], -1);
            Arrays.fill(jihoon[i], -1);
        }
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(matrix[i][j] == 'F'){
                    fireQ.add(new int[]{i, j});
                    fire[i][j] = 0;
                    continue;
                }
                if(matrix[i][j] == 'J'){
                    jihoonQ.add(new int[]{i, j});
                    jihoon[i][j] = 0;
                }
            }
        }
        while(!fireQ.isEmpty()){
            int[] cur = fireQ.poll();
            for(int d=0; d<4; d++){
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(nr < 0 || nr >= r || nc < 0 || nc >= c) continue;
                if(matrix[nr][nc] != '#' && fire[nr][nc] == -1){
                    fire[nr][nc] = fire[cur[0]][cur[1]] + 1;
                    fireQ.offer(new int[]{nr, nc});
                }
            }
        }
        while(!jihoonQ.isEmpty()){
            int[] cur = jihoonQ.poll();
            if(cur[0] == 0 || cur[0] == r-1 || cur[1] == 0 || cur[1] == c-1){
                System.out.print(jihoon[cur[0]][cur[1]] + 1);
                return;
            }
            for(int d=0; d<4; d++){
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(nr < 0 || nr >= r || nc < 0 || nc >= c) continue;
                if(matrix[nr][nc] != '#' && jihoon[nr][nc] == -1){
                    // 불이 확산되지 않은 칸이거나 지훈이보다 늦게 오면
                    if(fire[nr][nc] == -1 || fire[nr][nc] > jihoon[cur[0]][cur[1]] + 1){
                        jihoon[nr][nc] = jihoon[cur[0]][cur[1]] + 1;
                        jihoonQ.offer(new int[]{nr, nc});
                    }
                }
            }
        }
        System.out.print("IMPOSSIBLE");
    }
}