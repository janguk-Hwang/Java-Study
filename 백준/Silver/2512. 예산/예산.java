import java.io.*;
import java.util.*;

public class Main {
    static int n, m, max;
    static int[] request;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        request = new int[n]; max = 0;
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            request[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, request[i]);
        }
        m = Integer.parseInt(br.readLine());
        int start = 0;
        int end = max + 1;
        while(start + 1 < end){
            int mid = (start + end) / 2;
            long total = 0;
            for (int r : request) {
                total += Math.min(r, mid);
            }
            if (total <= m) start = mid;
            else end = mid;
        }
        System.out.println(start);
    }
}
