import java.util.*;
import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long gcd = gcdfunc(Math.max(a, b), Math.min(a, b));
        for(int i=0; i<gcd; i++) sb.append(1);
        System.out.println(sb);
    }

    public static long gcdfunc(long a, long b){
        if(a%b == 0) return b;
        return gcdfunc(b, a%b);
    }
}