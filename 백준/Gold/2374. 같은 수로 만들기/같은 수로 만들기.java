import java.io.*;
import java.util.*;

// 이전 값보다 커진 지점을 기준으로 연산횟수 증가 진행
// 1 10 1 있으면 앞의 1과 뒤의 1은 연산을 통해 함께 증가될 수 없으므로 10을 기준으로 따로 고려해야 한다
public class Main {
    static Stack<Integer> stack = new Stack<>();
    static int n, max;
    static long rst;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        rst = 0L;
        for(int i=0; i<n; i++){
            int temp = Integer.parseInt(br.readLine());
            max = Math.max(max, temp);
            if(!stack.isEmpty()){
                int top = stack.pop();
                if(top < temp) rst += temp - top;
            }
            stack.push(temp);
        }
        rst += max - stack.pop();
        System.out.print(rst);
    }
}