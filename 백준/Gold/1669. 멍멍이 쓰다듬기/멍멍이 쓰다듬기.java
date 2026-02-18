import java.io.*;
import java.util.*;

// 첫째 날과 마지막 날에는 무조건 1cm 만큼 늘릴 수 있다.
// 1 2 3 4 5 6 7... n... n-1 n-2 n-3... 3 2 1
// n^2
// 1 (1)
// 2 (1, 1)
// 3 (1, 1, 1)
// 4 (1, 2, 1)
// 5 (1, 2, 1, 1)
// 6 (1, 2, 2, 1)
// 7 (1, 2, 2, 1, 1)
// 8 (1, 2, 2, 2, 1)
// 9 (1, 2, 3, 2, 1)
// 10 (1, 2, 3, 2, 1, 1)
// 11 (1, 2, 3, 2, 2, 1)
// 12 (1, 2, 3, 3, 2, 1)
// 13 (1, 2, 3, 3, 2, 1, 1)
// 14 (1, 2, 3, 3, 2, 2, 1)
// 15 (1, 2, 3, 3, 3, 2, 1)
// 16 (1, 2, 3, 4, 3, 2, 1)
// 17 (1, 2, 3, 4, 3, 2, 1, 1)
// n^2일때는 같은 키를 늘리던 것을 최고점을 높임을 통해 일수를 줄일 수 있다.
// 16 (1, 2, 3, 3, 3, 3, 2, 1, 1) => 16 (1, 2, 3, 4, 3, 2, 1)
public class Main {
    static int x, y, target, rst;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        target = y - x;
        if(target == 0){
            System.out.print(0);
            return;
        }
        int top = 1;
        while((long)top * top <= target) top++;
        top--;
        rst = 2 * top - 1;
        target -= top * top;
        while(target > 0){
            for(int i=top; i>=1; i--){
                if(i <= target){
                    rst++;
                    target -= i;
                    break;
                }
            }
        }
        System.out.print(rst);
    }
}