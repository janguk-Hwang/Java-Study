import java.io.*;
import java.util.*;

public class Main {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        System.out.print(fact(n) / (fact(k) * fact(n - k)));
    }

    static long fact(int n){
        long rst = 1;
        for(int i=1; i<=n; i++) rst *= i;
        return rst;
    }
}