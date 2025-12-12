import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(br.readLine());
        Arrays.sort(arr);
        System.out.println(func());
    }

    static int func(){
        int left = 0;
        int right = 0;
        int minDiff = Integer.MAX_VALUE;
        while(right < n){
            int diff = arr[right] - arr[left];
            if(diff < m){
                right++;
                continue;
            }
            if(diff == m) return m;
            minDiff = Math.min(minDiff, diff);
            left++;
        }
        return minDiff;
    }
}