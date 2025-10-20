import java.io.*;
import java.util.*;

public class Main {
    static int n, m, sum, max;
    static int[] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        max = 0;
        for(int i=0; i<n; i++){
            sum = 0;
            for(int j=i+1; j<n-1; j++) {
                sum = arr[i] + arr[j];
                // 결정 문제: 기존 sum에 arr[mid]를 더해서 m을 넘지 않는가?
                // 하지만 end는 거짓이 보장되지 않는다.
                // ttttttffffff
                int start = j+1; int end = n-1;
                if((sum + arr[end]) <= m){
                    max = Math.max(max, sum + arr[end]);
                    continue;
                }
                while(start + 1 < end){
                    int mid = (start + end) / 2;
                    if((sum + arr[mid]) <= m) start = mid;
                    else end = mid;
                }
                if(sum + arr[end] <= m) max = Math.max(max, sum + arr[end]);
                if(sum + arr[start] <= m) max = Math.max(max, sum + arr[start]);
            }
        }
        System.out.print(max);
    }
}