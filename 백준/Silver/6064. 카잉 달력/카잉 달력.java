import java.io.*;
import java.util.*;

// n, m의 최소공배수가 마지막 해
public class Main {
    static int t, m, n, x, y;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            int minMul = m * n / gcd(m, n);
            boolean flag = false;
            for(int i=x; i<=minMul; i+=m){
                if((i-1) % n + 1 == y){
                    sb.append(i).append("\n");
                    flag = true;
                    break;
                }
            }
            if(!flag) sb.append(-1).append("\n");
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }

    public static int gcd(int a, int b){
        if(b == 0) return a;
        return gcd(b, a % b);
    }
}