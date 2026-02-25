import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] arr, diff;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        diff = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            diff[a - 1] += k;
            diff[b] -= k;
        }
        arr[0] += diff[0];
        for(int i=1; i<n; i++){
            // 변화가 생기는 곳에서만 diff[i]가 변함, 변화가 없는 곳은 diff[i] = 0
            diff[i] += diff[i - 1];
            arr[i] += diff[i];
        }
        for(int i : arr) sb.append(i).append(" ");
        System.out.print(sb);
    }
}