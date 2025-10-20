import java.io.*;
import java.util.*;

public class Main {
    static int a, b, c, max, mid, min;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        while(true){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            if(a == 0) break;
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            max = Math.max(Math.max(a, b), c);
            min = Math.min(Math.min(a, b), c);
            mid = a + b + c - max - min;
            if(max * max == (mid * mid + min * min)) sb.append("right").append("\n");
            else sb.append("wrong").append("\n");
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }
}