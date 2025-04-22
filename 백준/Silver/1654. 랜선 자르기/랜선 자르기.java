import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static long[] lan;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        lan = new long[k];
        long max = 0;
        for(int i=0; i<k; i++) {
            lan[i] = Long.parseLong(br.readLine());
            max = Math.max(max, lan[i]);
        }

        long start = 1;
        long end = max + 1;
        while(start + 1 < end){
            long mid = (start + end) / 2;
            long sum = 0;
            for(int i=0; i<k; i++) sum += lan[i] / mid;
            if(sum >= n) start = mid;
            else end = mid;
        }
        System.out.println(start);
    }
}