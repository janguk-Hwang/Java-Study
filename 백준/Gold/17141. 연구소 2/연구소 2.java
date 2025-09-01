import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int n, m;
    static int rst = Integer.MAX_VALUE;
    static int emptyCnt = 0;        // 빈 칸의 수
    static int[][] matrix;
    static boolean[] used;
    static List<int[]> virusPos = new ArrayList<>();    // 바이러스를 둘 수 있는 좌표 리스트
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if(matrix[i][j] == 0) emptyCnt++;
                if(matrix[i][j] == 2) virusPos.add(new int[]{i, j});
            }
        }
        used = new boolean[virusPos.size()];
        combination(0, 0);
        System.out.print(rst == Integer.MAX_VALUE ? -1 : rst);
    }

    public static void combination(int depth, int idx){
        // 종료 조건
        if(depth == m){
            bfs();
            return;
        }
        // 가능한 경우 모두 선택
        for(int i=idx; i<virusPos.size(); i++){
            used[i] = true;
            combination(depth + 1, i + 1);
            used[i] = false;
        }
    }

    public static void bfs(){
        int tempEmptyCnt = emptyCnt;
        boolean[][] visited = new boolean[n][n];
        int[][] tempMatrix = new int[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                tempMatrix[i][j] = matrix[i][j] == 2 ? 0 : matrix[i][j];
            }
        }

        Queue<int[]> q = new LinkedList<>();
        for(int i=0; i<virusPos.size(); i++){
            if(used[i]){
                int[] v = virusPos.get(i);
                tempMatrix[v[0]][v[1]] = 2;
                q.add(new int[]{v[0], v[1], 0});
                visited[v[0]][v[1]] = true;
            }
            else tempEmptyCnt++;
        }

        if(tempEmptyCnt == 0){
            System.out.print(0);
            System.exit(0);
        }
        int time = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                int[] now = q.poll();
                for(int d=0; d<4; d++){
                    int nr = now[0] + dr[d];
                    int nc = now[1] + dc[d];
                    if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                    if(visited[nr][nc] || tempMatrix[nr][nc] == 1) continue;
                    visited[nr][nc] = true;
                    if(tempMatrix[nr][nc] == 0) tempEmptyCnt--;
                    q.add(new int[]{nr, nc});
                }
                if(tempEmptyCnt == 0){
                    rst = Math.min(rst, time + 1);
                }
            }
            time++;
            if(time >= rst) return;
        }
    }
}