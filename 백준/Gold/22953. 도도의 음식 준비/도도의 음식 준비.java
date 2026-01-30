import java.util.*;
import java.io.*;

public class Main {
    static int n, k, c;
    static long rst;
    static int[] arr, praise, newTime;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        rst = Long.MAX_VALUE;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[n];
        praise = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
        backtracking(0, c);
        System.out.print(rst);
    }

    static void backtracking(int depth, int remainPraise){
        if(depth == n-1){
            praise[depth] = remainPraise;
            binarySearch();
            return;
        }
        for(int i=0; i<=remainPraise; i++){
            praise[depth] = i;
            backtracking(depth + 1, remainPraise - i);
        }
    }

    static void binarySearch(){
        int min = Integer.MAX_VALUE;
        newTime = new int[n];
        for(int i=0; i<n; i++){
            int temp = arr[i] - praise[i];
            if(temp < 1) temp = 1;
            newTime[i] = temp;
            min = Math.min(min, temp);
        }
        // fffffttttt
        long start = 0; long end = (long) min * k;
        while(start + 1 < end){
            long mid = (start + end) / 2;
            // mid 시간에 k개 요리가 가능하다면
            if(isPossible(mid)) end = mid;
            else start = mid;
        }
        rst = Math.min(rst, end);
    }

    static boolean isPossible(long mid){
        long cnt = 0;
        for(int i : newTime){
            cnt += (mid / i);
            if(cnt >= k) return true;
        }
        return false;
    }
}