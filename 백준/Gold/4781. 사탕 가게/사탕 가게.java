import java.io.*;
import java.util.*;

// 배낭 문제는 배낭에 남은 무게와 담을 수 있는 물건이 변화한다.
// dp 배열 -> dp[i][w]: i번째 물건까지 고려했을 때, 배낭에 남은 무게 w를 초과하지 않고 넣을 수 있는 최대 가치
// dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-i번째 물건의 무게] + i번째 물건의 가치)
// 2중 for문에서 바깥 for문은 고려할 물건까지의 인덱스를 순회, 내부 for문은 무게를 순회
// 칼로리: 가치, 가격: 무게
// 그런데 이 문제는 같은 사탕을 여러 번 살 수 있으므로 점화식이 다름
// 각 동전은 무한대로 있고, 동전으로 목표 가격을 만드는 경우의 수와 같음
// 중복이 발생하지 않도록 바깥 for문에서는 사탕의 종류를 선택, 내부 for문에서는 해당 사탕의 가격부터 m원까지 해당 사탕을 선택한 경우 반영
// dp[i] = i원을 가지고 살 수 있는 최대 칼로리
public class Main {
    static int n, m;
    static int[] calorie, cost;
    static int[] dp;      // dp[i][w]: i번째 사탕까지 고려했을 때, 남은 돈을 초과하지 않고 살 수 있는 사탕의 최대 칼로리
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        while(true){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            if(n == 0) break;
            m = (int)(Double.parseDouble(st.nextToken()) * 100 + 0.5);
            dp = new int[m + 1];
            calorie = new int[n + 1];
            cost = new int[n + 1];
            for(int i=1; i<=n; i++){
                st = new StringTokenizer(br.readLine());
                calorie[i] = Integer.parseInt(st.nextToken());
                cost[i] = (int)(Double.parseDouble(st.nextToken()) * 100 + 0.5);
            }
            for(int i=1; i<=n; i++){
                for(int j=cost[i]; j<=m; j++){
                    dp[j] = Math.max(dp[j], dp[j - cost[i]] + calorie[i]);
                }
            }
            sb.append(dp[m]).append("\n");
        }
        System.out.print(sb);
    }
}