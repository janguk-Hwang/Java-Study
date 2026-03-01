import java.io.*;
import java.util.*;

// 미로의 두 구멍을 최단 거리로 연결할 때 지나지 않는 길을 표시할 것이다.
public class Main {
    static int n, m;
    static char[][] matrix;
    static boolean[][] visited;
    static int[][][] prev;
    static List<int[]> hole = new ArrayList<>();
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new char[n][m];
        visited = new boolean[n][m];
        prev = new int[n][m][2];    // 0: r, 1: c
        for(int i=0; i<n; i++) matrix[i] = br.readLine().toCharArray();
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if((i == 0 || i == n - 1 || j == 0 || j == m - 1) && matrix[i][j] == '.') {
                    hole.add(new int[]{i, j});
                }
            }
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{hole.get(0)[0], hole.get(0)[1]});
        visited[hole.get(0)[0]][hole.get(0)[1]] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(cur[0] == hole.get(1)[0] && cur[1] == hole.get(1)[1]) break;
            for(int d=0; d<4; d++){
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc]) continue;
                if(matrix[nr][nc] == '+') continue;
                visited[nr][nc] = true;
                prev[nr][nc][0] = cur[0];
                prev[nr][nc][1] = cur[1];
                q.add(new int[]{nr, nc});
            }
        }
        boolean[][] path = new boolean[n][m];
        int endR = hole.get(1)[0];
        int endC = hole.get(1)[1];
        while(!(endR == hole.get(0)[0] && endC == hole.get(0)[1])){
            // 경로에 포함된 칸은 true
            path[endR][endC] = true;
            int tempR = prev[endR][endC][0];
            int tempC = prev[endR][endC][1];
            endR = tempR;
            endC = tempC;
        }
        path[hole.get(0)[0]][hole.get(0)[1]] = true;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(matrix[i][j] == '.' && !path[i][j]) sb.append("@");
                else sb.append(matrix[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}