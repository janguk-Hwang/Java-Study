import java.io.*;
import java.util.*;

// B가 커서 단순 계산으로는 시간초과
// a^b를 a^(b/2)로 분할
public class Main {
    static final int mod = 1000;
    static int n;
    static long b;
    static int[][] matrix;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        b = Long.parseLong(st.nextToken());
        matrix = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) matrix[i][j] = Integer.parseInt(st.nextToken()) % mod;
        }
        int[][] ret = pow(matrix, b);
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++) sb.append(ret[i][j]).append(" ");
            sb.append("\n");
        }
        System.out.print(sb);
    }

    // O(log2)
    public static int[][] pow(int[][] a, long exp){
        // 지수가 1인 경우
        if(exp == 1L) return a;
        
        int[][] ret = pow(a, exp / 2);
        ret = multiply(ret, ret);
        
        // 지수가 홀수인 경우
        if(exp % 2 == 1L) ret = multiply(ret, matrix);

        return ret;
    }
    
    // 행렬끼리 1번 곱셈, O(n^3)
    // pow에서 지수가 홀수인 경우에 기존 행렬과 곱해야 해서 매개변수를 2개로
    public static int[][] multiply(int[][] x, int[][] y){
        int[][] ret = new int[n][n];
        // 행렬 곱 구현
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                for(int k=0; k<n; k++){
                    // 각각 행 증가, 열 증가
                    ret[i][j] += x[i][k] * y[k][j];
                    // 결과 %1000
                    ret[i][j] %= mod;
                }
            }
        }
        return ret;
    }
}