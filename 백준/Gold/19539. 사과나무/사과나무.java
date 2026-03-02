import java.io.*;
import java.util.*;

// 한 번에 1, 2씩 성장해야 하므로 총합은 3의 배수이어야 한다.
// 1과 2의 개수는 같아야 한다.
// 짝수여도 불가능한 경우가 있다. (2, 8)은 합이 3의 배수가 아니므로 불가하다. 그래서 총합이 3의 배수이면 짝수여도 불가능한 경우는 발생하지 않는다.
// 홀수 나무의 수가 총합 / 3보다 작거나 같아야 한다. 이보다 더 많은 수의 홀수 나무가 있으면 1을 채울 수 없으므로 불가능하다.
public class Main {
    static int n, sum, oddCnt;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            int temp = Integer.parseInt(st.nextToken());
            sum += temp;
            if(temp % 2 == 1) oddCnt++;
        }
        if(sum % 3 == 0 && oddCnt <= sum / 3) System.out.print("YES");
        else System.out.print("NO");
    }
}