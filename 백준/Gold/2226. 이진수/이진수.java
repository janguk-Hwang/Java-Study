import java.io.*;
import java.math.BigInteger;
import java.util.*;

// 00 -> 1010
// 01 -> 1001
// 10 -> 0110
// 11 -> 0101
/*
1
01      1개
1001        1개
01101001        3개
1001011001101001    5개
01101001100101101001011001101001    11개
1001011001101001011010011001011001101001100101101001011001101001    21개
~                                                                    ?개
홀수일때는 연속된 숫자(00, 11)의 개수, 짝수일때는 연속된 숫자의 개수 + 1  ->  이 방법은 불가능, 규칙을 알아내야 함.
n이 홀수이면 0 그룹의 수는 n-1번째의 0 그룹의 수 x 2 - 1
n이 짝수이면 0 그룹의 수는 n-1번째의 0 그룹의 수 x 2 + 1
n은 최대 1000이므로 대략 2^1000을 나타낼 수 있는 것은 BigInteger
 */
public class Main {
    static int n;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        n = Integer.parseInt(br.readLine());
        int i = 1;
        BigInteger num = BigInteger.ZERO;
        while(i < n){
            if(i % 2 == 1) num = num.multiply(BigInteger.TWO).add(BigInteger.ONE);
            else num = num.multiply(BigInteger.TWO).subtract(BigInteger.ONE);
            i++;
        }
        System.out.print(num);
    }
}