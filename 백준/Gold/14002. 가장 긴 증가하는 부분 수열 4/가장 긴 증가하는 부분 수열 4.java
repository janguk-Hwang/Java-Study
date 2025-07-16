import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr, dp, pre;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new int[n];
        pre = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
            pre[i] = -1;
        }
        int max = 1;
        int last = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<i; j++){
                // 숫자(인덱스)
                // j번째 숫자가 더 작고 j번째 숫자를 선택했을 때 부분 수열의 길이가 기존 dp[i]보다 더 길면 갱신
                // 갱신하면서 i번째 숫자의 이전 숫자는 j번째 숫자라는 것을 pre 배열에 저장
                // 최종적으로 가장 긴 증가하는 부분 수열인 경우의 i번째 숫자 이전의 j번째 숫자가 pre 배열에 저장
                if(arr[j] < arr[i] && dp[j] + 1 > dp[i]){
                    dp[i] = dp[j] + 1;
                    pre[i] = j;
                }
            }
            if(dp[i] > max){
                max = dp[i];
                last = i;
            }
        }
        sb.append(max).append("\n");
        Stack<Integer> stack = new Stack<>();
        while(last != -1){
            stack.push(arr[last]);
            last = pre[last];
        }
        while(!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }
//        List<Integer> list = new ArrayList<>();
//        while(last != -1){
//            list.add(arr[last]);
//            last = pre[last];
//        }
//        Collections.reverse(list);
//        for(Integer i : list){
//            sb.append(i).append(" ");
//        }
        System.out.print(sb);
    }
}