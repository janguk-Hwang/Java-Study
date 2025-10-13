import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] matrix;
    static int[][] dist;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        matrix = new int[n][n];
        dist = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if(matrix[i][j] == 1){
                    dist[i][j] = 1;
                }
            }
        }
        for(int k=0; k<n; k++){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(dist[i][k] == 1 && dist[k][j] == 1){
                        dist[i][j] = 1;
                    }
                }
            }
        }
        for(int i=0; i<n; i++){
            for(int temp : dist[i]) sb.append(temp).append(" ");
            sb.append("\n");
        }
        System.out.print(sb);
    }
}