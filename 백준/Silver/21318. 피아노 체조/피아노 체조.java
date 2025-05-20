import java.io.*;
import java.util.*;

public class Main {
    static int n, q;
    static int[] arr, prefixSum;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        prefixSum = new int[n+1];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=n; i++) arr[i] = Integer.parseInt(st.nextToken());
        for(int i=1; i<n; i++){
            if(arr[i] > arr[i+1]) prefixSum[i] = prefixSum[i - 1] + 1;  // prefixSum[i] = 1 ~ i 구간까지 누적 실수 개수
            else prefixSum[i] = prefixSum[i-1];
        }
        q = Integer.parseInt(br.readLine());
        while(q-- > 0){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            sb.append(prefixSum[y-1] - prefixSum[x-1]).append("\n");
        }
        System.out.print(sb);
    }
}