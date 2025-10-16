import java.io.*;
import java.util.*;

public class Main {
    static int t, h, w, n, rst;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            int height = n % h;
            int ho = n / h +1;
            if(height == 0){
                height = h;
                ho = n / h;
            }
            rst = height * 100 + ho;
            sb.append(rst).append("\n");
        }
        System.out.print(sb);
    }
}