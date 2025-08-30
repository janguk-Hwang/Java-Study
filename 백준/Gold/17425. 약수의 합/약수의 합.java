import java.io.*;
import java.util.*;

// f(x)는 자연수 x의 약수의 총합
// g(x)는 x보다 작은 모든 자연수의 f(x)의 총합
// 미리 각 숫자의 약수의 총합을 구해야 한다.
// 그런데 테스트 케이스가 많은 경우, 똑같은 연산을 많이 반복해야 한다.
// 어차피 gSum[]은 누적되는 값이므로 누적합을 미리 구해서 효율적으로 진행
public class Main {
    static int MAX = 1_000_000;
    static int t;
    static long[] divisorSum, gSum;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        divisorSum = new long[MAX+1];
        gSum = new long[MAX+1];
        func();
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            int n = Integer.parseInt(br.readLine());
            sb.append(gSum[n]).append("\n");
        }
        System.out.print(sb);
    }

    // 1부터 MAX까지의 수들의 약수 합에 누적시켜나감(배수들을 소수가 아님을 체크해 나가는 에라토스테네스의 체와 비슷하다.)
    // 배수 순회 방식은 배수들의 약수 합에 누적시켜 나가는 방식
    public static void func(){
        for(int i=1; i<=MAX; i++){
            // i의 배수들에 i를 누적시킴
            for(int j=i; j<=MAX; j+=i){
                divisorSum[j] += i;
            }
        }
        // 누적합 사용
        for(int i=1; i<=MAX; i++) gSum[i] = gSum[i-1] + divisorSum[i];
    }
}