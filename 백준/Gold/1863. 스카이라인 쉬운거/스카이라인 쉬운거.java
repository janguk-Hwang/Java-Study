import java.io.*;
import java.util.*;

// 건물이 최소한 몇 개 있는지 알아내기
public class Main {
    static int n, cnt;
    static Stack<Integer> stack = new Stack<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        cnt = 0;
        n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            // y가 스택의 top보다 높이가 낮으면 건물이 끝났다는 것
            while(!stack.isEmpty() && stack.peek() > y){
                stack.pop();
            }
            // 건물이 시작될 때는 아래 5번 건물처럼 유지되던 건물이 없을 때와(스택에 건물이 없을 때)
            // 이전에 유지되던 건물보다 더 높은 건물이 시작되는 경우가 있다.
            // 1. 유지되던 건물이 없는 경우 (stack.isEmpty())
            // ..........................
            // .....XX.........XXX.......
            // .XXX.XX.......5555555.....
            // xxxxxxxxxx....5555555XXXXX
            // 2. 이전 건물보다 더 높은 건물이 시작되는 경우 (stack.peek() < y)
            // ..........................
            // .....22.........333.......
            // .111.22.......XX333XX.....
            // X111X22XXX....XX333XXXXXXX
            if(y > 0 && (stack.isEmpty() || stack.peek() < y)){
                stack.push(y);
                cnt++;
            }
        }
        System.out.print(cnt);
    }
}