import java.io.*;
import java.util.*;

public class Main {
    static int d, p;
    static int[] len, capacity;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        d = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        len = new int[p];
        capacity = new int[p];
        for(int i=0; i<p; i++){
            st = new StringTokenizer(br.readLine());
            len[i] = Integer.parseInt(st.nextToken());
            capacity[i] = Integer.parseInt(st.nextToken());
        }
        // 길이 i를 만들 수 있는 최대 수도관 용량
        int[] dp = new int[d + 1];
        // 길이 i를 만들 수 없을 수 있으므로 초기값은 0
        Arrays.fill(dp, 0);
        for(int i=0; i<p; i++){
            int l = len[i];
            int c = capacity[i];
            // 중복 방지를 위해 뒤에서부터
            for(int j=d; j>=l; j--){
                // i번째 파이프가 길이가 j이면 i번째 파이프와 기존 dp 값과 비교하여 최적해로 갱신
                if(j == l) dp[j] = Math.max(dp[j], c);
                // j - l길이를 만들 수 있으면 현재 파이프를 붙이는 선택과 기존 값을 비교해 더 좋은 용량을 선택한다.
                else if(dp[j - l] > 0) dp[j] = Math.max(dp[j], Math.min(dp[j - l], c));
            }
        }
        System.out.println(dp[d]);
    }
}