import java.io.*;
import java.util.*;

// 나열해보면 규칙을 발견할 수 있다.
// n%4 = 1 → 1
// n%4 = 2 → n+1
// n%4 = 3 → 0
// n%4 = 0 → n
public class Main {
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            sb.append(xorOneToN(f) ^ xorOneToN(s-1)).append("\n");
        }
        sb.setLength(sb.length()-1);
        System.out.println(sb);
    }

    static public int xorOneToN(int num){
        int remainder = num % 4;
        if(remainder == 0) return num;
        if(remainder == 1) return 1;
        if(remainder == 2) return num + 1;
        return 0;
    }
}