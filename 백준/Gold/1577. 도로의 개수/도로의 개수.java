import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k;
    static boolean[][][] isConstruction;
    static long[][] dp;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        isConstruction = new boolean[n + 1][m + 1][2];
        k = Integer.parseInt(br.readLine());
        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            // 행
            if(a == c){
                int temp = Math.min(b,d);
                isConstruction[a][temp][0] = true;
            }
            // 열
            else{
                int temp = Math.min(a,c);
                isConstruction[temp][b][1] = true;
            }
        }
        dp = new long[n + 1][m + 1];
        dp[0][0] = 1;
        for(int i=0; i<=n; i++){
            for(int j=0; j<=m; j++){
                // 세로로 이동
                if(i > 0) if(!isConstruction[i-1][j][1]) dp[i][j] += dp[i-1][j];
                // 가로로 이동
                if(j > 0) if(!isConstruction[i][j-1][0]) dp[i][j] += dp[i][j-1];
            }
        }
        System.out.print(dp[n][m]);
    }
}