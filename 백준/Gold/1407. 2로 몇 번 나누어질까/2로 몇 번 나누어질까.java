import java.io.*;
import java.util.*;

// 범위가 엄청 크기 때문에 순회하는 방식으로는 불가능하다.
// 배수 순회 방식으로 2의 제곱수들로 순회한다고 해도 각 수가 가지고 있는 2의 개수를 저장할 카운트 배열을 만들면 메모리가 너무 많이 사용된다.
// 4천조
// 수학적으로 접근해야 한다. 도토리 숨기기 문제처럼
// 25라는 숫자가 있다고 할 때, 1부터 25까지의 수 중에서 2를 최소 하나씩 가지고 있는 수는 25 / 2로 12개가 있다.
// 마찬가지로 1부터 25까지의 수 중에서 2를 최소 2개씩 가지고 있는 수는 25 / 4이다.
// ~~~ 25 / 8
// ~~~ 25 / 16
// 위와 같은 방식으로 1부터 25까지의 수들이 가지고 있는 2의 개수를 빠르게 구할 수 있다.
// 2의 제곱수들로 나눠서 2의 개수를 구하기 때문이다.
// 문제에서는 주어진 구간에 대해서만 출력하라고 했기 때문에 1~B까지 수들이 가지고 있는 2의 개수에 1 ~ A-1까지 수들이 가지고 있는 2의 개수를 빼면
// 자연스럽게 A~B구간의 수들이 가지고 있는 2의 개수를 구할 수 있다.
public class Main {
    static long a, b;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());
        // a ~ b
        System.out.print(oneToN(b) - oneToN(a-1));
    }

    static long oneToN(long n){
        long sum = 0; long div = 1; long prev = 0;
        while(div <= n){
            sum += (n / div) * (div - prev);
            prev = div;
            div *= 2;
        }
        return sum;
    }
}