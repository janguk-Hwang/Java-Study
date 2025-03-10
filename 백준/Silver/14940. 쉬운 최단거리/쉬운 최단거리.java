import java.io.*;
import java.util.*;

// 모든 지점에 대해서 목표지점까지의 거리를 구하여라
// 오직 가로와 세로로만 움직일 수 있다고 하자
public class Main {
    static int n, m;
    static int[][] matrix;
    static StringTokenizer st;
    static boolean[][] visited;
    static int[][] dist;
    static int[] dr = {1, 0, -1, 0};    // 상하좌우의 순서가 바뀌어도 답이 같은가? 그렇다면 이유는?
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 세로의 크기
        m = Integer.parseInt(st.nextToken());   // 가로의 크기
        matrix = new int[n][m];
        dist = new int[n][m];

        //입력 받기 (0: 갈 수 없는 땅, 1: 갈 수 있는 땅, 2: 도착점)
        for (int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for (int j=0; j<m; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 도착점 찾기
        int goalRow = -1, goalCol = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 2) {
                    goalRow = i;
                    goalCol = j;
                    break;
                }
            }
            if (goalRow != -1) break; // 도착점 찾으면 종료
        }

        // BFS 시작
        bfs(goalRow, goalCol);

        // 결과 출력
        for(int i=0; i<n; i++) {
            for (int j = 0; j < m; j++) {
                // 갈 수 있는 땅이지만 dist가 0이면 갈 수 없는 땅
                if (matrix[i][j] == 1 && dist[i][j] == 0) {
                    System.out.print(-1 + " ");
                } 
                else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    // BFS로 도착점에서 각 위치까지의 거리 계산
    public static void bfs(int r, int c){
        visited = new boolean[n][m];
        Queue<int[]> q = new LinkedList<int[]>();  // 큐 생성
        q.add(new int[]{r, c});  // 도착점부터 시작

        // 도착점의 거리는 0으로 초기화
        dist[r][c] = 0;
        visited[r][c] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();   // r, c가 계속 값이 이동해야 함으로 q에서 뽑아서 현재 좌표를 할당
            r = cur[0];
            c = cur[1];

            // 상하좌우 탐색
            for (int i=0; i<4; i++){
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 내부에 있고, 방문하지 않은 곳이며, 갈 수 있는 땅이면
                if(nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc] && matrix[nr][nc] != 0){
                    visited[nr][nc] = true;     // 방문처리
                    q.add(new int[]{nr, nc});
                    dist[nr][nc] = dist[r][c] + 1;  // 거리 갱신
                }
            }
        }
    }
}
