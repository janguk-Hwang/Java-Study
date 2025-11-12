import java.io.*;
import java.util.*;

// 아무것도 남지 않을 때까지 1개를 제거하면 그 정수가 점수가 되고, 두 정수를 제거하면 두 정수의 곱이 점수가 된다.
// -3 -1 1 5 5
// 정렬된 상태에서 음수는 왼쪽부터 두 개씩 곱한다.
// 0이 나올수도 있다.
// 혼자 남은 음수는 0이랑 곱해서 0으로 만들고 남은 0은 혼자 제거해서 변화가 없게 하고,
// 양수는 오른쪽부터 2개씩 곱한다.
// 양수가 1이면 다른 수와 곱하지 않고 그냥 더한다.
// 혼자남은 양수는 혼자 제거한다.
public class Main {
    static List<Integer> plus = new ArrayList<>();
    static List<Integer> minus = new ArrayList<>();
    static int n, zeroCnt;
    static long rst;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            int num = Integer.parseInt(br.readLine());
            if(num > 0) plus.add(num);
            else if(num < 0) minus.add(num);
            else zeroCnt++;
        }
        plus.sort(Collections.reverseOrder());
        minus.sort(null);
        rst = 0;
        for(int i=0; i<plus.size(); i++){
            if(i + 1 < plus.size()){
                int a = plus.get(i); int b = plus.get(i+1);
                // 하나라도 1이라면 따로 더해주는 것이 더 최적이다.
                if(a == 1 || b == 1){
                    rst += a + b;
                    i++;
                    continue;
                }
                rst += (long) a * b;
                // 두 양수를 처리했을 때만 i를 한 번 더 증가
                i++;
                continue;
            }
            // 하나가 남으면 그대로 더함
            rst += plus.get(i);
        }
        for(int i=0; i<minus.size(); i++){
            if(i + 1 < minus.size()){
                rst += (long) minus.get(i) * minus.get(i + 1);
                i++;
                continue;
            }
            // 0이 없으면 그대로 더하기, 하나라도 있으면 혼자남은 음수와 0을 곱함
            if(zeroCnt == 0) rst += minus.get(i);
        }
        System.out.print(rst);
    }
}