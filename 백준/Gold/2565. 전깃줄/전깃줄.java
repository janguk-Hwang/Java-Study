import java.io.*;
import java.util.*;

public class Main {
    public static int N; // 전깃줄 개수
    public static int answer = 0;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        int[] dp = new int[N]; // LIS(최장 증가 부분 수열) 길이를 저장할 배열
        int[][] arr = new int[N][2]; // 전깃줄 정보를 저장할 2차원 배열 (A, B 전봇대)

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // A 전봇대 위치
            arr[i][1] = Integer.parseInt(st.nextToken()); // B 전봇대 위치
        }

        // A 전봇대 기준으로 오름차순 정렬
        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);

        // LIS (Longest Increasing Subsequence) 알고리즘 적용
        for (int i = 0; i < N; i++) {
            dp[i] = 1; // 최소 1개는 포함되므로 초기값 1
            for (int j = 0; j < i; j++) {
                if (arr[i][1] > arr[j][1]) { // 이전 B 전봇대 값보다 현재 B 전봇대 값이 크다면
                    dp[i] = Math.max(dp[i], dp[j] + 1); // 더 긴 증가 부분 수열 갱신
                }
            }
        }

        // 최장 증가 부분 수열 갱신
        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, dp[i]);
        }

        // 최소로 제거해야 하는 전깃줄 개수 = 전체 전깃줄 개수 - 유지할 수 있는 전깃줄 개수(LIS 길이)
        System.out.println(N - max);
    }
}
