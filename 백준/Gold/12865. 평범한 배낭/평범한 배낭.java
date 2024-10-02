import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int K = Integer.parseInt(in[1]);

        int[] dp = new int[K + 1];

        //
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int W = Integer.parseInt(input[0]);
            int V = Integer.parseInt(input[1]);

            //반대로 순회하면서 dp의 최대값 갱신
            //준서가 버틸 수 있는 최대 무게부터 각 입력줄마다 무게를 빼가면서
            for (int j = K; j >= W; j--) {
                //ex) dp[100] vs dp[94]+13중에 더 큰것을 dp값으로 -> 해당 물건을 넣을 거냐 넣지 않을 것이냐를 판단
                dp[j] = Math.max(dp[j], dp[j - W] + V);
            }
        }
        
        System.out.println(dp[K]);
    }
}