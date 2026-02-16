import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        System.out.print(fact(n));
    }

    public static long fact(int n){
        long rst = 1;
        for(int i=2; i<=n; i++) rst *= i;
        return rst;
    }
}