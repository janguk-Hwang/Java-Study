import java.io.*;
import java.util.*;

public class Main {
    static int t;
    static int MAX = 5000;
    static List<Integer> primes = new ArrayList<>();
    static boolean[] isNotPrime = new boolean[MAX + 1];
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        prime();
        t = Integer.parseInt(br.readLine());
        int primeSize = primes.size();
        while(t-- > 0){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int[] a = new int[primeSize];
            int[] b = new int[primeSize];
            calcPrimeCnt(n1, r1, a);
            calcPrimeCnt(n2, r2, b);

            boolean flag = true;
            for(int i=0; i<primeSize; i++){
                if(a[i] > 0 && b[i] > 0){   // 두 조합에 대해 공통된 소수가 있는 경우
                    flag = false;
                    break;
                }
            }
            sb.append(flag ? 1 : 0).append("\n");
        }
        System.out.print(sb);
    }

    public static void prime() {
        isNotPrime[0] = isNotPrime[1] = true;
        for(int i=2; i<=MAX; i++){
            if(!isNotPrime[i]){
                primes.add(i);
                for(int j=i*2; j<=MAX; j+=i){   // i의 배수들은 소수가 아님
                    isNotPrime[j] = true;
                }
            }
        }
    }

    // r!, (n-r)!, n!
    public static void calcPrimeCnt(int n, int r, int[] arr) {
        legendre(arr, n, 1);
        legendre(arr, r, -1);
        legendre(arr, n - r, -1);
    }

    public static void legendre(int[] arr, int n, int cnt) {
        for(int i=0; i<primes.size(); i++){
            int p = primes.get(i);
            if(p > n) break;    // n보다 큰 소수는 n!에서 나올 수 없음
            int exp = 0;
            int temp = n;
            // 르장드르의 공식
            while(temp > 0){
                exp += temp / p;
                temp /= p;
            }
            arr[i] += exp * cnt;    // arr[2]에는 소수 2가 n!에 나오는 횟수가 저장
        }
    }
}