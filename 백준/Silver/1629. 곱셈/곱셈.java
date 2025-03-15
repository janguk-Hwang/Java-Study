import java.io.*;
import java.util.*;

public class Main {
    static Long a, b, c;
    static StringTokenizer st = null;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ");
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());
        c = Long.parseLong(st.nextToken());
        // Math.pow(a, b) 를 c로 나눈 나머지를 출력해야 한다.
        // 값이 너무 커지므로 분할을 해야 한다.
        // (a * b) % c = (a % c * b % c) % c
        // 10^11 % c = (10^5 % c * 10^6 % c) % c
        // (10^5 % c * 10^6 % c) % c = ((10^2 % c * 10^3 % c) % c * (10^3 % c * 10^3 % c) % c) % c
        
        System.out.println(rc(a, b, c));
    }

    // 재귀를 통해 분할 정복
    public static long rc(long a, long b, long c) {
        // 곱하는 횟수가 0이면 1 반환(a^1 = 0)
        if (b == 0) {
            return 1;
        }

        // b가 1이면 a % c를 바로 반환
        if (b == 1) {
            return a % c;
        }

        // b가 홀수인 경우 (a * (a^(b-1) % c)) % c
        if (b % 2 == 1) {
            return (a * rc(a, b - 1, c)) % c;
        }

        // b가 짝수인 경우 (a^(b/2) % c) * (a^(b/2) % c) % c
        else {
            long half = rc(a, (b / 2), c);
            return (half * half) % c;
        }
    }
}
