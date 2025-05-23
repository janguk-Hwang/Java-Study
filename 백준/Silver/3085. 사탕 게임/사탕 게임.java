import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static char[][] matrix;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        matrix = new char[n][n];
        for(int i=0; i<n; i++){
            String line = br.readLine();
            for(int j=0; j<n; j++){
                matrix[i][j] = line.charAt(j);
            }
        }
        int max = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(j+1 < n){
                    swap(i, j, i, j+1);
                    max = Math.max(max, getMax());
                    swap(i, j, i, j+1); // 되돌리기
                }
                if(i+1 < n){
                    swap(i, j, i+1, j);
                    max = Math.max(max, getMax());
                    swap(i, j, i+1, j);
                }
            }
        }
        System.out.print(max);
    }

    public static void swap(int x1, int y1, int x2, int y2){
        char temp = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = temp;
    }

    public static int getMax(){
        int result = 1;
        // 행
        for(int i=0; i<n; i++){
            int cnt = 1;
            for(int j=n-1; j>0; j--){
                if(matrix[i][j] == matrix[i][j-1]) cnt++;
                else{
                    result = Math.max(result, cnt);
                    cnt = 1;
                }
            }
            result = Math.max(result, cnt);
        }
        // 열
        for(int j=0; j<n; j++){
            int cnt = 1;
            for(int i=n-1; i>0; i--){
                if(matrix[i][j] == matrix[i-1][j]) cnt++;
                else{
                    result = Math.max(result, cnt);
                    cnt = 1;
                }
            }
            result = Math.max(result, cnt);
        }
        return result;
    }
}