import java.io.*;
import java.util.*;

public class Main {
    static int t;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a == c || b == c){
                sb.append("YES" + "\n");
                continue;
            }
            if(isPossible(a, b, c)) sb.append("YES" + "\n");
            else sb.append("NO" + "\n");
        }
        System.out.print(sb);
    }

    static boolean isPossible(int a, int b, int c){
        // c가 두 물통 중 큰 값보다 크면 불가능
        if(c > Math.max(a, b)) return false;
        // 항상 a <= b 되도록 정렬
        int diff = Math.abs(b - a);
        // 두 물통의 용량이 같은 경우
        if(diff == 0) return c % a == 0;
        int i = gcd(a, b);
        return c % i == 0;
    }

    static int gcd(int a, int b){
        while(b != 0){
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}