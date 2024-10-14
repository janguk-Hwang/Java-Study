import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long N = sc.nextLong();
        long A = sc.nextLong();

        //덧셈역은 N - A
        long addInverse = (N - A) % N;

        //확장 유클리드 알고리즘을 이용해 곱셈역 구하기
        long[] result = extendGCD(A, N);
        long gcd = result[0];
        long multiInverse = result[1];

        if (gcd != 1) {     //A와 N이 서로소가 아니면 -1
            multiInverse = -1;
        }
        else {
            multiInverse = (multiInverse % N + N) % N;  //음수일 경우 양수로 변환
        }
        
        System.out.println(addInverse + " " + multiInverse);
    }

    //확장 유클리드
    public static long[] extendGCD(long a, long b) {
        if (b == 0) {
            return new long[] {a, 1, 0}; //gcd, x, y 반환
        }
        long[] result = extendGCD(b, a % b);
        long gcd = result[0];
        long x = result[2];
        long y = result[1] - (a / b) * result[2];
        return new long[] {gcd, x, y};
    }
}