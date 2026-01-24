import java.io.*;
import java.util.*;

public class Main {
    static int d, n, m;
    static int[] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        d = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(br.readLine());
        Arrays.sort(arr);
        // fffffttttt
        int start = 0; int end = d + 1;
        while(start + 1 < end){
            int mid = (start + end) / 2;    // 점프 하한선
            int cnt = 0;
            int prev = 0;
            for(int i=0; i<n; i++){
                if(arr[i] - prev < mid){
                    cnt++;
                    if(cnt > m) break;
                }
                else prev = arr[i];
            }
            if(d - prev < mid) cnt++;
            if(cnt <= m) start = mid;
            else end = mid;
        }
        System.out.print(start);
    }
}