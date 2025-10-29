import java.io.*;
import java.util.*;

// 3 5 7 9 11 13 17 19 23
// 48
// 45
// 62
// 41
// 60
// 36
// 59
// 23
// 투 포인터로 n보다 커질 때까지 합을 구하다가 같거나 커지면 앞에서부터 합이 n보다 같거나 작아질 때까지 빼준다.
// '연속된' 소수들의 합이기 때문에 투 포인터로 가능하다.
public class Main {
    static int n, cnt;
    static List<Integer> primes = new ArrayList<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        sieve();
        int left = 0; int right = 0;
        int sum = 0; cnt = 0;
        while(true){
            if(sum >= n){
                if(sum == n) cnt++;
                sum -= primes.get(left++);
            }
            else{
                if(right == primes.size()) break;
                sum += primes.get(right++);
            }
        }
        System.out.print(cnt);
    }

    static void sieve(){
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        for(int i=2; i*i<=n; i++){
            if(isPrime[i]){
                for(int j=i*i; j<=n; j+=i) isPrime[j] = false;
            }
        }
        for(int i=2; i<=n; i++) if(isPrime[i]) primes.add(i);
    }
}