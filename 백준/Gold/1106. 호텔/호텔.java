import java.io.*;
import java.util.*;

// 무조건 돈에 정수배 만큼을 투자할 수 있다.
// 각 도시에는 무한의 잠재적인 고객이 있다.
// 그리디방식으로 효율이 좋은 도시에서부터 홍보하고 부족한 나머지 고객을 다음으로 효율이 좋은 도시에서 영업하면
// 항상 최적이 나오지 않는다.
// 목표 영업 고객 수: 25명
// 도시 1: 비용 8, 고객 13
// 도시 2: 비용 5, 고객 8
// 그리디 방식: 8 + 5 * 2 = 18
// 실제 최적해: 8 * 2 = 16
// dp를 사용해 최적해 구함
public class Main {
    static int[] dp;    // dp[i]: 고객 i명을 얻기 위한 최소 비용
    static int c, n;
    static int[][] arr;     // [도시 번호][0 or 1]: 0: 도시 비용, 1: 도시 고객 수
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        dp = new int[c + 101];
        arr = new int[n][2];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<2; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }
        Arrays.fill(dp, c * 100 + 1);
        dp[0] = 0;
        // 모든 도시에 대해서
        for(int i=0; i<n; i++){
            for(int j=arr[i][1]; j<c+101; j++){
                // j명의 고객을 유치하기 위한 최소 비용은 기존 비용과 이번 도시를 한 번 더 이용하는 경우 중 더 효율이 좋은 경우 선택
                dp[j] = Math.min(dp[j], dp[j - arr[i][1]] + arr[i][0]);
            }
        }
        int rst = c * 100 + 1;
        for(int i=c; i<c+101; i++) rst = Math.min(rst, dp[i]);
        System.out.print(rst);
    }
}