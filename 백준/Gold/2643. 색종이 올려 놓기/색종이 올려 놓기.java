import java.io.*;
import java.util.*;

// 그리디하게 가능한가?
// 그리디하게 한다면 어떤 기준으로 그리디하게 해야 할까?
// 남은 색종이 중에 가장 큰 색종이를 올려야 하는 것은 당연하지만 애매한 경우는 어떻게 순서를 정해야 할까?
// ex) 10 10 색종이가 있고 그 다음에 7 8 을 올려야 하나? 9 6를 올려야 하나?
// 두 변의 길이의 합은 같지만 이후에 어떤 색종이가 올 수 있는지에 따라 어떤 선택이 더 최적인지는 알 수 없다.
// 그리디 방식이 아니라면 어떤 방식으로 해야 할까?
// 보통 이렇게 그리디 방식으로 최적해가 보장되지 않는 경우에는 dp를 생각해볼 수 있다.
// 작은 부분 문제로 큰 부분 문제를 해결하는 dp 관점에서 생각해보면 dp는 이렇게 정의해볼 수 있다.
// i번째 색종이를 맨 위에 두었을 때, 쌓을 수 있는 색종이의 최대 수
// 이렇게 정의해야 작은 부분문제로 큰 부분 문제를 해결할 수 있다.
public class Main {
    static int n;
    static int[][] arr;
    static int[] dp;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n][2];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            // 회전할 수 있으므로 0, 1은 서로 바뀔 수 있다.
            int b1 = Integer.parseInt(st.nextToken());
            int b2 = Integer.parseInt(st.nextToken());
            arr[i][0] = Math.max(b1, b2);
            arr[i][1] = Math.min(b1, b2);
        }
        Arrays.sort(arr, (a,b) -> a[0] == b[0] ? a[1]-b[1] : a[0]-b[0]);
        dp = new int[n];
        Arrays.fill(dp, 1);
        for(int i=0; i<n; i++){
            for(int j=0; j<i; j++){
                if((arr[i][0] >= arr[j][0] && arr[i][1] >= arr[j][1]) ||
                        arr[i][0] >= arr[j][1] && arr[i][1] >= arr[j][0]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int rst = 0;
        for(Integer i : dp) rst = Math.max(rst, i);
        System.out.print(rst);
    }
}