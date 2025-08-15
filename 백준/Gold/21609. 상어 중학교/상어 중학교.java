import java.io.*;
import java.util.*;

// 검은색 블록: -1, 무지개 블록: 0, 빈 칸: -2
public class Main {
    static int n, m;
    static boolean[][] visited;
    static int[][] matrix;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int totalScore = 0;
        while(true){
            Block best = findBestGroup();
            if(best == null) break;     // 그룹이 없으면 종료
            for(int[] temp : best.blocks){
                matrix[temp[0]][temp[1]] = -2;
            }
            totalScore += best.size * best.size;

            // 중력 작용
            gravity();
            // 90도 반시계 회전
            rotate();
            // 중력 작용
            gravity();
        }
        System.out.print(totalScore);
    }

    // bfs 함수에서 전달받은 그룹 중 가장 최적의 그룹을 반환
    public static Block findBestGroup(){
        visited = new boolean[n][n];
        PriorityQueue<Block> pq = new PriorityQueue<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(matrix[i][j] <= 0 || visited[i][j]) continue; // 일반 블록만 시작점
                Block group = bfs(i, j);
                if(group.size < 2) continue;    // 그룹은 블록의 개수가 2개 이상
                pq.add(group);
            }
        }
        if(pq.isEmpty()) return null;
        return pq.poll();
    }

    // 블록 그룹 탐색((r, c)칸에서 무지개, 같은 색의 일반 블록을 포함하여 가장 큰 그룹을 Block 타입으로 반환)
    public static Block bfs(int r, int c){
        boolean[][] tempVisited = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>();
        List<int[]> blocks = new ArrayList<>();
        int color = matrix[r][c];
        int rainbowCnt = 0;
        int size = 1;
        int baseR = r;
        int baseC = c;
        visited[r][c] = true;
        tempVisited[r][c] = true;
        q.add(new int[]{r, c});
        blocks.add(new int[]{r, c});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int d=0; d<4; d++){
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                // 유효하지 않거나 이미 방문했거나 검은색 블록이거나 빈칸이거나 색이 다른 칸이면 건너뜀
                if(nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
                if(tempVisited[nr][nc]) continue;
                if(matrix[nr][nc] == -1 || matrix[nr][nc] == -2) continue;
                if(matrix[nr][nc] != 0 && matrix[nr][nc] != color) continue;
                // 모두 통과했으면 방문
                tempVisited[nr][nc] = true;
                q.add(new int[]{nr, nc});
                blocks.add(new int[]{nr, nc});
                size++;
                if(matrix[nr][nc] == 0){
                    rainbowCnt++;
                    continue;
                }
                // 무지개 블록이 아닌 일반 블록이면 visited 배열 방문처리
                visited[nr][nc] = true;
                // 기준 블록
                if(nr < baseR || (nr == baseR && nc < baseC)){
                    baseR = nr;
                    baseC = nc;
                }
            }
        }
        return new Block(size, rainbowCnt, baseR, baseC, blocks);
    }

    // 90도 반시계 방향으로 회전
    public static void rotate(){
        int[][] temp = new int[n][n];
        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                temp[n-1-c][r] = matrix[r][c];
            }
        }
        matrix = temp;
    }

    // 중력 작용
    // 검은색 블록을 제외한 모든 블록이 행의 번호가 큰 칸으로 이동한다. 이동은 다른 블록이나 격자의 경계를 만나기 전까지 계속 된다.
    public static void gravity(){
        for(int c=0; c<n; c++){
            // 맨 아랫칸 바로 윗 칸부터 맨 윗칸까지
            for(int r=n-2; r>=0; r--){
                // 무지개 블록, 일반 블록만
                if(matrix[r][c] >= 0){
                    int nr = r;
                    while(true){
                        // 범위를 벗어나지 않고 이동할 칸이 빈 칸이면 이동
                        if(nr + 1 < n && matrix[nr + 1][c] == -2){
                            matrix[nr + 1][c] = matrix[nr][c];
                            matrix[nr][c] = -2;     // 기존 자리는 빈 칸으로
                            nr++;
                            continue;
                        }
                        break;
                    }
                }
            }
        }
    }

    public static class Block implements Comparable<Block>{
        int size, rainbowCnt, baseR, baseC;
        List<int[]> blocks;  // 블록 그룹에 속한 블록들의 좌표를 저장하는 리스트
        public Block(int size, int rainbowCnt, int baseR, int baseC, List<int[]> blocks){
            this.size = size; this.rainbowCnt = rainbowCnt; this.baseR = baseR; this.baseC = baseC;
            this.blocks = blocks;
        }
        @Override
        public int compareTo(Block o) {
            if(this.size != o.size) return Integer.compare(o.size, this.size);
            if(this.rainbowCnt != o.rainbowCnt) return Integer.compare(o.rainbowCnt, this.rainbowCnt);
            if(this.baseR != o.baseR) return Integer.compare(o.baseR, this.baseR);
            return Integer.compare(o.baseC, this.baseC);
        }
    }
}