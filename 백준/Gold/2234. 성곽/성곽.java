import java.io.*;
import java.util.*;

// 비트를 사용해서 방향 처리 가능
// 11 -> 1 + 2 + 8 -> 동쪽 빼고 다 벽이다. -> 11과 & 연산하여 0이 나오는 방향이 벽이 없는 방향이다.
public class Main {
    static int[] wallD = {2, 4, 8, 1};  // 북, 동, 남, 서
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] matrix;
    static int[][] markIdx;
    static boolean[][] visited;
    static int n, m, roomCnt, maxRoom, collapseMaxRoom;
    static HashMap<Integer, Integer> map = new HashMap<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        matrix = new int[n + 1][m + 1];
        visited = new boolean[n + 1][m + 1];
        markIdx = new int[n + 1][m + 1];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        roomCnt = 0;
        int idx = 1;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!visited[i][j]){
                    bfs(i, j, idx++);
                    roomCnt++;
                }
            }
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                // 각 칸에서 오른쪽, 아래의 벽만 허물음
                for(int d=1; d<=2; d++){
                    if((matrix[i][j] & wallD[d]) != 0){
                        // 범위 유효 여부 확인
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                        if(markIdx[i][j] == markIdx[nr][nc]) continue;
                        collapseMaxRoom = Math.max(collapseMaxRoom, map.get(markIdx[i][j]) + map.get(markIdx[i + dr[d]][j + dc[d]]));
                    }
                }
            }
        }
        System.out.print(roomCnt + "\n" + maxRoom + "\n" + collapseMaxRoom);
    }

    static void bfs(int r, int c, int idx){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        visited[r][c] = true;
        markIdx[r][c] = idx;
        int roomSize = 0;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            roomSize++;
            for(int d=0; d<4; d++){
                // 벽이 없는 방향으로 이동 가능
                if((wallD[d] & matrix[cur[0]][cur[1]]) == 0) {
                    int nr = cur[0] + dr[d];
                    int nc = cur[1] + dc[d];
                    if (nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc]) continue;
                    visited[nr][nc] = true;
                    markIdx[nr][nc] = idx;
                    q.add(new int[]{nr, nc});
                }
            }
        }
        map.put(idx, roomSize);
        maxRoom = Math.max(maxRoom, roomSize);
    }
}