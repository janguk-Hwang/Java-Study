import java.io.*;
import java.util.*;

public class Main {
    static char[][] matrix;
    static int n, m;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new char[n][m];
        for(int i=0; i<n; i++) matrix[i] = br.readLine().toCharArray();
        int temp1 = 0; int temp2 = 0; int total = 0;
        for(int i=0; i<n-1; i++){
            for(int j=0; j<m; j++){
                if(matrix[i][j] == 'X' && matrix[i+1][j] == '.') temp1++;
                else{
                    total += temp1 / 2;
                    temp1 = 0;
                }
                if(matrix[i][j] == '.' && matrix[i+1][j] == 'X') temp2++;
                else{
                    total += temp2 / 2;
                    temp2 = 0;
                }
            }
        }

        temp1 = 0; temp2 = 0;
        for(int i=0; i<m-1; i++){
            for(int j=0; j<n; j++){
                if(matrix[j][i] == 'X' && matrix[j][i+1] == '.') temp1++;
                else{
                    total += temp1 / 2;
                    temp1 = 0;
                }
                if(matrix[j][i] == '.' && matrix[j][i+1] == 'X') temp2++;
                else{
                    total += temp2 /2;
                    temp2 = 0;
                }
            }
        }
        System.out.print(total);
    }
}