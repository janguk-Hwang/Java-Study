import java.io.*;
import java.util.*;

public class Main {
    static int n, s, minLength;
    static long[] arr, prefixSum;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        arr = new long[n]; prefixSum = new long[n + 1];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            prefixSum[i + 1] = prefixSum[i] + arr[i];
        }
        minLength = 100_001;
        // 결정 문제: 합이 s보다 큰가?
        // ffffffftttttttt
        for(int left=0; left<n; left++){
            int start = left;
            int end = n;
            while(start + 1 < end){
                int mid = (start + end) / 2;
                long sum = prefixSum[mid] - prefixSum[left];
                if(sum >= s) end = mid;
                else start = mid;
            }
            if(end <= n && prefixSum[end] - prefixSum[left] >= s){
                minLength = Math.min(minLength, end - left);
            }
        }
        if(minLength == 100_001){
            System.out.print(0);
            return;
        }
        System.out.print(minLength);
    }
}