import java.io.*;
import java.util.*;

public class Main {
    static int[] kdc = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] kdr = {-1, 1, -2, 2, -2, 2, -1, 1};
    static int[] qdc = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] qdr = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int n, m;
    static int[][] matrix;
    static boolean[][] horse;
    static List<int[]> queen = new ArrayList<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n+1][m+1];
        horse = new boolean[n+1][m+1];
        st = new StringTokenizer(br.readLine());
        int temp = Integer.parseInt(st.nextToken());
        for(int i=0; i<temp; i++){
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            matrix[row][col] = 1;
            queen.add(new int[]{row, col});
        }

        st = new StringTokenizer(br.readLine());
        temp = Integer.parseInt(st.nextToken());
        for(int i=0; i<temp; i++){
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            matrix[row][col] = 1;
            horse[row][col] = true;
            for(int j=0; j<8; j++){
                int nr = row + kdr[j];
                int nc = col + kdc[j];
                if(nr >= 1 && nr <= n && nc >= 1 && nc <= m){
                    matrix[nr][nc] = 1;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        temp = Integer.parseInt(st.nextToken());
        for(int i=0; i<temp; i++){
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            matrix[row][col] = 1;
            horse[row][col] = true;
        }

        for(int[] queen : queen){
            int row = queen[0];
            int col = queen[1];
            for(int j=0; j<8; j++){
                int nr = row;
                int nc = col;
                while(true){
                    nr += qdr[j];
                    nc += qdc[j];
                    if(nr < 1 || nr > n || nc < 1 || nc > m) break;
                    if(horse[nr][nc]) break;
                    matrix[nr][nc] = 1;
                }
            }
        }

        int cnt = 0;
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(matrix[i][j] == 0) cnt++;
            }
        }
        System.out.print(cnt);
    }
}