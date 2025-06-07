import java.io.*;
import java.util.*;

public class Main {
    static int MAX = 5000;
    static List<Integer> primes = new ArrayList<>();
    static boolean[] isNotPrime = new boolean[MAX + 1];

    public static void main(String[] args) throws IOException {
        sieve();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        int primeCount = primes.size();
        int[] a = new int[primeCount];
        int[] b = new int[primeCount];

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            Arrays.fill(a, 0);
            Arrays.fill(b, 0);

            getPrimeExponentDiff(n1, r1, a);
            getPrimeExponentDiff(n2, r2, b);

            boolean coprime = true;
            for (int i = 0; i < primeCount; i++) {
                if (a[i] > 0 && b[i] > 0) {
                    coprime = false;
                    break;
                }
            }

            sb.append(coprime ? "1\n" : "0\n");
        }

        System.out.print(sb);
    }

    static void sieve() {
        isNotPrime[0] = isNotPrime[1] = true;
        for (int i = 2; i <= MAX; i++) {
            if (!isNotPrime[i]) {
                primes.add(i);
                for (int j = i * 2; j <= MAX; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }
    }

    static void getPrimeExponentDiff(int n, int r, int[] arr) {
        addFactorialExponents(arr, n, 1);
        addFactorialExponents(arr, r, -1);
        addFactorialExponents(arr, n - r, -1);
    }

    static void addFactorialExponents(int[] arr, int n, int cnt) {
        for (int i = 0; i < primes.size(); i++) {
            int p = primes.get(i);
            if (p > n) break;

            int exp = 0;
            int k = n;
            while (k > 0) {
                exp += k / p;
                k /= p;
            }
            arr[i] += exp * cnt;
        }
    }
}