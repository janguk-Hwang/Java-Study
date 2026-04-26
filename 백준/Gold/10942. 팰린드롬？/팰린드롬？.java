import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] arr;
    static boolean[][] dp;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) arr[i] = Integer.parseInt(st.nextToken());
        dp = new boolean[n + 1][n + 1];
        // 기저 사례(길이가 1인 구간은 무조건 팰린드롬)
        for(int i=1; i<=n; i++) dp[i][i] = true;
        // 기저 사례(길이가 2인 구간은 두 수가 같으면 팰린드롬)
        for(int i=1; i<n; i++) if(arr[i] == arr[i + 1]) dp[i][i + 1] = true;
        for(int i=3; i<=n; i++){    // 길이
            for(int j=1; j<=n-i+1; j++){    // 인덱스
                int k = j + i - 1;      // 구간의 끝 인덱스
                // 내부 부분이 true이고 양 끝도 서로 같다면 팰린드롬
                if(arr[j] == arr[k] && dp[j + 1][k - 1]) dp[j][k] = true;
            }
        }
        m = Integer.parseInt(br.readLine());
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if(dp[s][e]) sb.append("1").append("\n");
            else sb.append("0").append("\n");
        }
        System.out.print(sb);
    }
}