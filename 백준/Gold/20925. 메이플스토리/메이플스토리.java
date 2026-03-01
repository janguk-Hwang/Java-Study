import java.io.*;
import java.util.*;

// 사냥터의 특징을 입장에 필요한 최소 경험치와 1분마다 얻는 경험치로 간략화했다.
// 관리해야 할 상태는 시간, 현재 위치한 사냥터, 현재 경험치
// dp[i][j]: 시간 i가 지났을 때, j번 사냥터에 있을 때의 최대 경험치
public class Main {
    static int n, t;
    static int[][] ce;
    static int[][] moveCost;
    static int[][] dp;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        ce = new int[n][2];     // 0: c(입장에 필요한 최소 경험치), 1: e(1분마다 얻는 경험치)
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            ce[i][0] = Integer.parseInt(st.nextToken());
            ce[i][1] = Integer.parseInt(st.nextToken());
        }
        // 각 사냥터 사이를 이동하는 데 걸리는 시간
        moveCost = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) moveCost[i][j] = Integer.parseInt(st.nextToken());
        }
        dp = new int[t + 1][n];
        for(int i=0; i<=t; i++) Arrays.fill(dp[i], -1);
        for(int i=0; i<n; i++) if(ce[i][0] == 0) dp[0][i] = 0;
        for(int i=0; i<=t; i++){
            for(int j=0; j<n; j++){
                if(dp[i][j] == -1) continue;
                // 현재 사냥터에서 계속 사냥
                if(i + 1 <= t) dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j] + ce[j][1]);
                // 다음 사냥터로 이동
                for(int k=0; k<n; k++){
                    if(k == j) continue;
                    int nextT = i + moveCost[j][k];
                    // 이동 후 시간이 넘지 않아 사냥이 가능하고, 현재 경험치가 입장에 필요한 경험치를 만족하면
                    if(nextT <= t && dp[i][j] >= ce[k][0]){
                        dp[nextT][k] = Math.max(dp[nextT][k], dp[i][j]);
                    }
                }
            }
        }
        int rst = 0;
        for(int i=0; i<=t; i++){
            for(int j=0; j<n; j++){
                rst = Math.max(rst, dp[i][j]);
            }
        }
        System.out.print(rst);
    }
}