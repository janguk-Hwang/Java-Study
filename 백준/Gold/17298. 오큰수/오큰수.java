import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr, result;
    static Stack<Integer> stack = new Stack<>();
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        result = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
        for(int i = n-1; i>=0; i--){
            // 스택에는 자신보다 큰 수만 남김
            // top에는 자신보다 오른쪽에 있으면서 가장 가까이 있는 자신보다 큰 수
            while(!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }
        for(Integer i : result) sb.append(i).append(" ");
        System.out.print(sb);
    }
}