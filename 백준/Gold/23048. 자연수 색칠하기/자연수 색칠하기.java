import java.io.*;
import java.util.*;

// 6, 25는 서로수이다.
// 6은 2와 같은 색으로 칠해져있을 것이고 25는 5와 같은 색으로 칠해져있을 것이므로 서로수지만 색이 다른것이 보장된다.
// 이렇게 에라토스테네스의 체를 활용해서 어떤 소수의 배수들을 이 소수와 같은 색으로 칠해주면 서로 서로수인 두 합성수는 다른 색이 보장된다.
// 소수끼리는 서로수기 때문에 매번 새로운 색을 사용해야 한다.
public class Main {
    static int n, colorCnt;
    static int[] color;
    static boolean[] isNPrime;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        color = new int[n + 1];
        isNPrime = new boolean[n + 1];
        for(int i=2; i*i<=n; i++){
            if(!isNPrime[i]){
                for(int j=i*i; j<=n; j+=i){
                    isNPrime[j] = true;
                }
            }
        }
        colorCnt = 1;       // 1
        color[1] = 1;       // 1의 색은 1
        for(int i=2; i<=n; i++){
            // 소수이면 새로운 색 사용, 배수들도 같은 색으로
            if(!isNPrime[i]){
                colorCnt++;
                // 이전에 다른 소수를 통해 다른 색으로 칠해지지 않은 수들만 칠함
                for(int j=i; j<=n; j+=i) if(color[j] == 0) color[j] = colorCnt;
            }
        }
        sb.append(colorCnt).append("\n");
        for(int i=1; i<=n; i++) sb.append(color[i]).append(" ");
        sb.setLength(sb.length() - 1);
        System.out.print(sb);
    }
}