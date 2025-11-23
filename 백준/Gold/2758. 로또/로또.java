import java.io.*;
import java.util.*;

public class Main {
    static int t;
    static long[][] dp; // dp[i][j]: i까지 고려했을 때 길이가 j인 수열의 개수
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        int[] arrN = new int[t];
        int[] arrM = new int[t];
        int maxN = 0; int maxM = 0;
        for(int i=0; i<t; i++){
            st = new StringTokenizer(br.readLine());
            arrN[i] = Integer.parseInt(st.nextToken());
            arrM[i] = Integer.parseInt(st.nextToken());
            maxN = Math.max(maxN, arrN[i]);
            maxM = Math.max(maxM, arrM[i]);
        }
        dp = new long[maxM + 1][maxN + 1];
        for(int x=1; x<=maxM; x++) dp[x][1] = 1;
        // 수열의 길이 순회 2 ~ n개
        for(int i=2; i<=maxN; i++){
            // 마지막 숫자 순회 1 ~ m까지
            for(int j=1; j<=maxM; j++){
                int limit = j / 2;
                for(int p=1; p<=limit; p++) dp[j][i] += dp[p][i-1];
            }
        }
        for(int i=0; i<t; i++){
            long sum = 0;
            for(int j=1; j<=arrM[i]; j++) sum += dp[j][arrN[i]];
            sb.append(sum).append("\n");
        }
        System.out.print(sb);
    }
}