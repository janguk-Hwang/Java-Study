import java.io.*;
import java.util.*;

// 수열 A와 수열 B가 공통으로 갖는 부분 수열들 중 사전 순으로 가장 나중인 것을 구하세요
// 뒤에서부터 두 수열을 비교해 나가면서 숫자가 같으면 해당 숫자에 이전까지의 최적해를 이어붙인 수열과
// 두 숫자가 달라서 수열 A에서 건너뛴 경우와 수열 B에서 건너뛴 경우 중 최적해를 정해야 한다.
// 사전순이므로 뒤에서부터 참조해야 작은 부분 문제로 큰 부분 문제를 해결할 수 있다.
// 점화식
// a[i] == b[j]인 경우
// Math.max(a[i] + dp[i+1][j+1], dp[i+1][j], dp[i][j+1])
// a[i] != b[j]인 경우
// Math.max(dp[i+1][j], dp[i][j+1])
public class Main {
    static int n, m;
    static int[] a, b;
    static List<Integer>[][] dp;        // 수열을 담기 위해 리스트로
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        a = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) a[i] = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());
        b = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++) b[i] = Integer.parseInt(st.nextToken());

        dp = new ArrayList[n + 1][m + 1];
        for(int i=0; i<=n; i++) for(int j=0; j<=m; j++) dp[i][j] = new ArrayList<>();
        for(int i=n-1; i>=0; i--){
            for(int j=m-1; j>=0; j--){
                List<Integer> temp = new ArrayList<>();
                if(a[i] == b[j]){
                    temp.add(a[i]);
                    temp.addAll(dp[i+1][j+1]);
                }
                temp = order(temp, dp[i+1][j]) ? temp : dp[i+1][j];
                temp = order(temp, dp[i][j+1]) ? temp : dp[i][j+1];
                dp[i][j] = temp;
            }
        }
        sb.append(dp[0][0].size()).append("\n");
        if(!dp[0][0].isEmpty()) for(Integer i : dp[0][0]) sb.append(i).append(" ");
        System.out.print(sb);
    }

    // a 수열이 사전순으로 더 나중이면 true 반환, 먼저면 false 반환
    static boolean order(List<Integer> a, List<Integer> b){
        int len = Math.min(a.size(), b.size());
        for(int i=0; i<len; i++){
            if(!a.get(i).equals(b.get(i))){
                if(a.get(i) > b.get(i)) return true;
                else return false;
            }
        }
        // 같으면 길이가 더 짧은 수열이 사전순으로 먼저
        return a.size() > b.size();
    }
}