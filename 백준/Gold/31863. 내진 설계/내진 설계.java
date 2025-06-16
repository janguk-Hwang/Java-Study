import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int n, m, earthquakei, earthquakej;
    static int building, collapse;
    static char[][] matrix;
    static int[][] life;
    static boolean[][] collapsed;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new char[n][m];
        collapsed = new boolean[n][m];
        life = new int[n][m];
        building = 0; collapse = 0;
        for(int i=0; i<n; i++){
            matrix[i] = br.readLine().toCharArray();
            for(int j=0; j<m; j++){
                if(matrix[i][j] == '@'){
                    earthquakei = i;
                    earthquakej = j;
                }
                if(matrix[i][j] == '*'){
                    life[i][j] = 1;
                    building++;
                }
                if(matrix[i][j] == '#'){
                    life[i][j] = 2;
                    building++;
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        for(int d=0; d<4; d++){
            int nr = earthquakei;
            int nc = earthquakej;
            for(int i=0; i<2; i++){
                nr += dr[d];
                nc += dc[d];
                if(nr < 0 || nr >= n || nc < 0 || nc >= m || matrix[nr][nc] == '|') break;
                if(matrix[nr][nc] == '*' || matrix[nr][nc] == '#'){
                    life[nr][nc]--;
                    if(life[nr][nc] == 0){
                        collapse++;
                        collapsed[nr][nc] = true;
                        q.add(new int[]{nr, nc});       // 무너진 건물 저장(여진)
                    }
                }
            }
        }

        while(!q.isEmpty()){
            int[] temp = q.poll();
            int r = temp[0];
            int c = temp[1];
            for(int d=0; d<4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(nr < 0 || nr >= n || nc < 0 || nc >= m || matrix[nr][nc] == '|') continue;
                if(matrix[nr][nc] == '*' || matrix[nr][nc] == '#'){
                    life[nr][nc]--;
                    if(life[nr][nc] == 0){
                        collapse++;
                        collapsed[nr][nc] = true;
                        q.add(new int[]{nr, nc});
                    }
                }
            }
        }
        System.out.print(collapse + " " + (building - collapse));
    }
}