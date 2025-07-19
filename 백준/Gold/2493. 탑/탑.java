import java.io.*;
import java.util.*;

// 레이저 신호를 수신한 탑들의 번호를 하나의 빈칸을 사이에 두고 출력
// 만약 레이저 신호를 수신하는 탑이 존재하지 않으면 0을 출력
public class Main {
    static Stack<Integer> stack = new Stack<>();
    static int n;
    static int[] buildings;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        buildings = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) buildings[i] = Integer.parseInt(st.nextToken());
        for(int i=0; i<n; i++){
            int curHeight = buildings[i];
            while(!stack.isEmpty() && buildings[stack.peek()] < buildings[i]){
                stack.pop();
            }
            if(stack.isEmpty()){
                sb.append("0").append(" ");
            }
            if(!stack.isEmpty()){
                sb.append((stack.peek() + 1)).append(" ");
            }
            stack.push(i);
        }
        System.out.print(sb);
    }
}