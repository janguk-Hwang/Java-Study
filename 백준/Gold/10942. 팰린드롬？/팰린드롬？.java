import java.io.*;

public class Main {
    static int[] num;
    static int[][] dp;
    static int N;
    static int S;
    static int E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        num = new int[N + 1];
        dp = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            num[i] = Integer.parseInt(input[i - 1]);
        }

        palindrome();

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            String[] in = br.readLine().split(" ");
            S = Integer.parseInt(in[0]);
            E = Integer.parseInt(in[1]);

            if (dp[S][E] == 1) {
                bw.write("1\n");
            } else {
                bw.write("0\n");
            }
        }

        bw.flush();
        bw.close();
    }

    public static void palindrome() {
        // 1. 길이가 1인 경우 모든 수는 자기 자신으로 팰린드롬이다.
        for (int i = 1; i <= N; i++) {
            dp[i][i] = 1;
        }

        // 2. 길이가 2인 경우 두 수가 같으면 팰린드롬이다.
        for (int i = 1; i < N; i++) {
            if (num[i] == num[i + 1]) {
                dp[i][i + 1] = 1;
            }
        }

        // 3. 길이가 3 이상인 경우 팰린드롬 판별
        for (int len = 2; len < N; len++) {  // 길이를 2부터 늘려가며 검사
            for (int start = 1; start + len <= N; start++) {
                int end = start + len;
                if (num[start] == num[end] && dp[start + 1][end - 1] == 1) {
                    dp[start][end] = 1;
                }
            }
        }
    }
}
