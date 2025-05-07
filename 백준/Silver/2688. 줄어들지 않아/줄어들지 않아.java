import java.io.*;
import java.math.BigInteger;

public class Main {
    static int t, n;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BigInteger factorial(int n) {
        BigInteger fact = BigInteger.ONE;
        for (int i = 1; i <= n; i++) fact = fact.multiply(BigInteger.valueOf(i));
        return fact;
    }

    public static BigInteger binomialCoefficient(int n, int r) {
        if (r == n) return BigInteger.ONE;
        // C(n, r) = n! / (r! * (n - r)!)
        BigInteger numerator = factorial(n);
        BigInteger denominator = factorial(r).multiply(factorial(n - r));
        return numerator.divide(denominator);
    }

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        for(int i=0; i<t; i++){
            n = Integer.parseInt(br.readLine());
            sb.append(binomialCoefficient(10+n-1, n)).append("\n");
        }
        System.out.println(sb);
    }
}