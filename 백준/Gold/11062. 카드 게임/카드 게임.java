import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] card;
    static int[][] dp;  // 해당 구간에서 근우가 얻을 수 있는 최댓값을 저장
    static int n, t;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        for(int i=0; i<t; i++) {
            n = Integer.parseInt(br.readLine());
            card = new int[n];
            dp = new int[n+1][n+1];
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                card[j] = Integer.parseInt(st.nextToken());
            }
            solve(0, n-1, true);
            System.out.println(dp[0][n-1]); // n개의 카드에서 근우가 얻을 수 있는 최대 점수 출력
        }
    }

    // left ~ right까지 카드가 있을 때 근우가 얻을 수 있는 최대 점수를 반환
    // solve 함수가 실행된다는 것은 각자 최선의 선택으로 카드를 뽑는다는 의미
    static int solve(int left, int right, boolean turn) {
        // 종료 조건
        if(left > right) return 0;
        // 메모이제이션
        if(dp[left][right] != 0) return dp[left][right];

        // 근우
        // 근우가 얻을 수 있는 최대 점수는 왼쪽카드를 선택했을 때 명우가 나머지에서 최선의 선택을 하는 경우와
        // 오른쪽카드를 선택했을 때 명우가 나머지에서 최선의 선택을 하는 경우 중에 더 큰 값이다.
        if(turn) {
            return dp[left][right] = Math.max(card[left] + solve(left+1, right, false), card[right] + solve(left, right-1, false));
        }
        // 명우
        // dp는 근우의 점수를 의미하기 때문에 근우처럼 card[]를 더하지 않는다.
        // dp값은 명우가 왼쪽카드를 선택했을 경우와 오른쪽카드를 선택했을 경우 중 더 작은 값이다. 명우는 dp값(근우의 점수)을 낮춰야 하기 때문
        else {
            return dp[left][right] = Math.min(solve(left+1, right, true), solve(left, right-1, true));
        }
    }
}