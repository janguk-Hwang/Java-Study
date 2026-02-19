import java.io.*;
import java.math.BigInteger;
import java.util.*;

// 가로로 자른 횟수: w, 세로로 자른 횟수: h
// w, h번 자른 후 생기는 색종이의 수 = (w + 1) * (h + 1)
// 총 자르는 횟수: n, 만들 색종이의 수: k
// (w + 1) * (n - w + 1) = k
// w^2 − nw + (k − n − 1) = 0
public class Main {
    static int n;
    static long k;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Long.parseLong(st.nextToken());
        long start = 0;
        long end = n;
        while(start <= end){
            long mid = (start + end) / 2;
            // (mid + 1) * (n - mid + 1);
            BigInteger temp = BigInteger.valueOf(mid + 1).multiply(BigInteger.valueOf(n - mid + 1));
            BigInteger K = BigInteger.valueOf(k);
            int rst = temp.compareTo(K);
            if(rst == 0){
                System.out.print("YES");
                return;
            }
            if(rst > 0) end = mid - 1;
            else start = mid + 1;
        }
        System.out.print("NO");
    }
}