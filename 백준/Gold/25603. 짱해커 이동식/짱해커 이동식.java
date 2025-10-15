import java.io.*;
import java.util.*;

public class Main {
    static int n, k, max;
    static int[] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        max = 0;
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }
        int start = 0; int end = max;
        while(start + 1 < end){
            int mid = (start + end) / 2;
            if(isPossible(mid)) end = mid;
            else start = mid;
        }
        System.out.print(end);
    }

    static boolean isPossible(int mid){
        int cnt = 0;
        for(int i : arr){
            if(i > mid){
                cnt++;
                if(cnt >= k) return false;
            }
            else cnt = 0;
        }
        return true;
    }
}