import java.util.*;
import java.io.*;

public class Main {
    static int m, n, mid;
    static long cnt;
    static int[] snack;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        snack = new int[n];
        int max = 0;
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<n; i++){
            snack[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, snack[i]);
        }

        // ttttttttffffffffff
        int start = 1;
        int end = max + 1;
        while(start + 1 < end){
            cnt = 0;
            mid = (start + end) / 2;
            for(int i=0; i<n; i++){
                cnt += snack[i] / mid;
            }
            // 가능
            if(cnt >= m) start = mid;
            else end = mid;
        }
        cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt += snack[i] / start;
        }
        System.out.println(cnt >= m ? start : 0);

    }
}
