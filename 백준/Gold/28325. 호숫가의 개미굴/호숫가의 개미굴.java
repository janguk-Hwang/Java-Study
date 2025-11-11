import java.io.*;
import java.util.*;

// 각 노드에서 쪽방이 많아서 i번째 방을 비우는 것이 나은지, 채우는 것이 나은지 결정한다.
// i번째 방이 쪽방이 하나면 쪽방을 채우거나 i번째 방을 채울 수 있을 텐데 양 옆의 방에 따라 어떤 것이 최적인지 달라질 수 있다.
public class Main {
    static int n;
    static long[] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new long[n + 1];
        for(int i=1; i<=n; i++) arr[i] = Long.parseLong(st.nextToken());

        // 1번 방이 채워지는 경우

        // 2번 방이 빈 경우
        long pre0 = arr[2];
        // 2번 방이 찬 경우
        long pre1 = 0;
        for(int i=3; i<=n-1; i++){
            long cur0 = Math.max(pre0, pre1) + arr[i];
            long cur1 = pre0 + 1;
            pre0 = cur0; pre1 = cur1;
        }
        long rst1 = 1 + Math.max(pre0, pre1) + arr[n];

        // 1번 방이 비어지는 경우

        pre0 = arr[2];
        pre1 = 1;
        for(int i=3; i<=n; i++){
            long cur0 = Math.max(pre0, pre1) + arr[i];
            long cur1 = pre0 + 1;
            pre0 = cur0; pre1 = cur1;
        }
        long rst2 = arr[1] + Math.max(pre0, pre1);
        System.out.print(Math.max(rst1, rst2));
    }
}