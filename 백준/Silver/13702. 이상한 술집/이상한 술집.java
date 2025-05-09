import java.io.*;
import java.util.*;

// 분배 후 주전자에 막걸리가 조금 남아 있다면 그냥 막걸리를 버리기로 한다
// 단, 항상 N ≤ K 이다
public class Main {
    static int n, k;
    static int[] arr;
    static long sum;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n];
        long max = 0;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        long start = 0;
        long end = max + 1;
        while(start + 1 < end){
            sum = 0;
            long mid = (start + end) / 2;
            for(int i=0; i<n; i++) sum += arr[i] / mid;
            if(sum >= k) start = mid;
            else end = mid;
        }
        System.out.print(start);
    }
}