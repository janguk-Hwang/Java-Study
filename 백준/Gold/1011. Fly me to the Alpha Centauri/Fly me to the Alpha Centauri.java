import java.io.*;
import java.util.*;

// 최소 횟수로 이동해야 하기 때문에 최대한 높은 숫자까지 1씩 증가하다가 1씩 감소하여
// 1까지 감소해야 한다.
// 이상적으로는 1 2 3 4 ... n n-1 n-2 n-3 ... 1이 되어야 한다.
// 하지만 정확히 이동거리를 만들기 위해서는 이상적인 산 모양을 가지지 않고 중간 중간에 1씩 증가되어야 한다.
// 총합이 n^2인 경우에는 가운데 값을 증가시켜서 횟수를 줄일 수 있다.
public class Main {
    static int t;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int target = y - x;
            int sqrt = (int) Math.sqrt(target);
            // 가운데 값은 총합의 제곱근 이하의 정수 중 가장 큰 값
            if(target == sqrt * sqrt) sb.append(2 * sqrt - 1);
            else if(target <= sqrt*sqrt + sqrt) sb.append(2*sqrt);
            else sb.append(2*sqrt + 1);
            sb.append("\n");
        }
        System.out.print(sb);
    }
}