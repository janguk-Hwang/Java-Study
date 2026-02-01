import java.io.*;
import java.util.*;

// if day mod 7 = 4:
//     day = 금요일
// 순서를 바꿀 수 있다, 중간에 일이 금요일에 끝나면 가능
// next 배열에 dp를 복사해놓아서 dp가 갱신되고 해당 턴에서 또 사용하지 못하도록
public class Main {
    static int n;
    static boolean[] dp;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        dp = new boolean[7];
        dp[0] = true;
        for(int i=0; i<n; i++){
            int work = Integer.parseInt(st.nextToken());
            int remain = work % 7;
            boolean[] next = dp.clone();
            for(int j=0; j<7; j++){
                if(dp[j]) next[(j + remain) % 7] = true;
            }
            dp = next;
        }
        System.out.print(dp[4] ? "YES" : "NO");
    }
}