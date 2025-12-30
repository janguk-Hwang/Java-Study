import java.io.*;
import java.util.*;

public class Main {
    static long a, b;
    static boolean[] isPrime;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        a = Long.parseLong(st.nextToken()); b = Long.parseLong(st.nextToken());
        int maxSqrt = (int) Math.sqrt(b);
        isPrime = new boolean[maxSqrt + 1];
        sieve(maxSqrt);
        long count = 0;
        // 소수는 2부터
        for(int i=2; i<=maxSqrt; i++){
            if(!isPrime[i]) continue;
            long value = (long) i * i;
            while(value <= b){
                if(value >= a) count++;
                if(value > b / i) break;
                // ^3, ^4, ^5 ...
                value *= i;
            }
        }
        System.out.println(count);
    }

    static void sieve(int n){
        Arrays.fill(isPrime, true);
        for(int i=2; i*i<=n; i++){
            if(!isPrime[i]) continue;
            for(int j=i*i; j<=n; j+=i) isPrime[j] = false;
        }
    }
}