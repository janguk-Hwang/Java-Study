import java.io.*;
import java.util.*;

// 계속 서쪽으로만, 즉 도시 번호가 증가하는 순서대로만 이동하기로 하였다.
// n번 항로를 1~m번째로 이용했을 때의 먹게 되는 기내식의 점수의 총 합의 최대를 dp로 정의
// 2중 for문으로 고려할 항로를 순회, 항로를 1~m번째로 이용할 때의 순서를 순회
// 그러면 각 항로를 몇 번째로 방문하는 것이 최적인지 계산 가능
// dp[i][j] = Math.max(dp[i-1][j-1], dp[][]);
public class Main {
    static int INF = Integer.MIN_VALUE;
    static List<int[]> list = new ArrayList<>();
    static int n, m, k, rst;
    static int[][] dp;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dp = new int[n + 1][m + 1];
        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());   // 기내식 점수
            list.add(new int[]{a, b, c});
        }
        for(int i=1; i<=n; i++) Arrays.fill(dp[i], INF);
        dp[1][1] = 0;   // 출발점에서는 비행기를 타지 않았으므로 먹은 기내식 점수는 0
        for(int i=2; i<=m; i++){
            for(int[] temp : list){
                // 비행 항로가 개설되어 있지 않으면 건너뜀
                if(dp[temp[0]][i-1] == INF) continue;
                if(temp[0] >= temp[1]) continue;
                // 이전에 다른 경로로 도달한 경우와 비교 후 갱신
                dp[temp[1]][i] = Math.max(dp[temp[1]][i], dp[temp[0]][i-1] + temp[2]);
            }
        }
        // 여행 경로는 반드시 1번 도시에서 시작해서 N번 도시에서 끝나야 한다.
        // 꼭 m개의 도시를 거쳐야 최적이 아니다.
        for(int i=1; i<=m; i++) rst = Math.max(rst, dp[n][i]);
        System.out.print(rst);
    }
}