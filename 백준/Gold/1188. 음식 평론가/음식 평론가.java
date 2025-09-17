import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int needChop = m - 1;
        int unneededChop = gcd(n, m) - 1;
        System.out.print(needChop - unneededChop);
    }

    static int gcd(int a, int b){
        while(b != 0){
            int temp = a%b;
            a = b;
            b = temp;
        }
        return a;
    }
}