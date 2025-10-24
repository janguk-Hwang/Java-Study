import java.io.*;
import java.util.*;

// 방향이 바뀌었을 때, i-2번째 방향과 바뀐 방향이 같으면 방향을 바꾸고 1 블록만 이동하고 방향을 바꾼 것이다.
// dp[행][열][현재 방향][같은 방향으로 2칸 이상 이동 여부]: 가능한 경로의 개수
// 0: 북쪽 방향 1: 동쪽 방향
public class Main {
    static final int MOD = 100_000;
    static int w, h;        // (2 ≤ w, h ≤ 100)
    static int[][][][] dp;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        dp = new int[w + 1][h + 1][2][2];   // 행, 열, 현재 방향, 이전 방향
        for(int i=2; i<=w; i++){
            dp[i][1][0][0] = 1;
            dp[i][1][0][1] = 1;
        }
        for(int i=2; i<=h; i++){
            dp[1][i][1][0] = 1;
            dp[1][i][1][1] = 1;
        }
        for(int i=2; i<=w; i++){
            for(int j=2; j<=h; j++){
                // i, j를 북쪽으로 이동하여 도착했고, 이전에 방향을 바뀐 경우
                dp[i][j][0][0] = dp[i-1][j][1][1] % MOD;
                // i, j를 북쪽으로 이동하여 도착했고, 이전에 방향이 유지된 경우
                dp[i][j][0][1] = (dp[i-1][j][0][0] + dp[i-1][j][0][1]) % MOD;
                // i, j를 오른쪽으로 이동하여 도착했고, 이전에 방향이 바뀐 경우
                dp[i][j][1][0] = dp[i][j-1][0][1] % MOD;
                // i, j를 오른쪽으로 이동하여 도착했고, 이전에 방향이 유지된 경우
                dp[i][j][1][1] = (dp[i][j-1][1][0] + dp[i][j-1][1][1]) % MOD;
            }
        }
        int rst = 0;
        for(int i=0; i<2; i++) for(int j=0; j<2; j++) rst += dp[w][h][i][j];
        System.out.println(rst % MOD);
    }
}