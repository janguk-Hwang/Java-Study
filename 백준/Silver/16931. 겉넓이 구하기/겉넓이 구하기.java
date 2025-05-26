import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] matrix;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<m; j++) matrix[i][j] = Integer.parseInt(st.nextToken());
        }
        int total = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                for(int k=0; k<4; k++){
                    int nr = i + dr[k];
                    int nc = j + dc[k];
                    if(nr >= 0 && nr < n && nc >= 0 && nc < m) total += Math.max(0, matrix[i][j] - matrix[nr][nc]);
                    else total += matrix[i][j];
                }
            }
        }
        total += 2 * n * m;
        System.out.println(total);
    }
}