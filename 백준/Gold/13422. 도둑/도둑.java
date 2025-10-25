import java.io.*;
import java.util.*;

public class Main {
    static int t, n, m, k;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());

            int sum = 0;
            int cnt = 0;
            if(n == m){
                for(int i : arr) sum += i;
                if(sum < k) sb.append(1).append("\n");
                else sb.append(0).append("\n");
                continue;
            }
            for(int i=0; i<m; i++) sum += arr[i];
            if(sum < k) cnt++;
            // 시작점은 0번부터 n-1번까지 모두 고려돼야 함
            for(int i=1; i<n; i++){
                sum -= arr[i-1];
                sum += arr[(i+m-1) % n];
                if(sum < k) cnt++;
            }
            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
    }
}