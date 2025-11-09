import java.io.*;
import java.util.*;

// 두 수를 최대공약수, 최소공배수로 하는 두 자연수를 구해라.
// 여러 개 있는 경우에는 두 자연수의 합이 최소가 되는 두 수를 출력
// 두 수의 곱은 최대공약수, 최소공배수의 곱과 같다.
// b * s = x * y -> x와 y는 b의 배수, b * s의 약수이어야 한다.
public class Main {
    static int b, s;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        b = Integer.parseInt(st.nextToken());   // 최대공약수
        s = Integer.parseInt(st.nextToken());   // 최소공배수
        long bs = (long) b * s;
        long x = b; long y = s;
        for(long i=b; i * i <= bs; i+=b){
            // b * s의 약수이면
            if(bs % i == 0){
                long j = bs / i;
                if(gcd(i, j) == b){
                    // 두 수의 합이 더 작으면 갱신
                    if(x + y > i + j){
                        x = i; y = j;
                    }
                }
            }
        }
        System.out.print(x + " " + y);
    }

    static long gcd(long a, long b){
        while(b != 0){
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}