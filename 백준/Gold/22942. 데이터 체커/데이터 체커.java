import java.io.*;
import java.util.*;

public class Main {
    static Stack<Integer> stack = new Stack<>();
    static int n;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        Circle[] arr = new Circle[n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());   // 원의 중심
            int b = Integer.parseInt(st.nextToken());   // 반지름
            int left = a - b;
            int right = a + b;
            arr[i] = new Circle(left, right);
        }
        // left 오름차순 left가 같으면 right 내림차순 정렬
        // 현재 원이 이전 원의 내부에 포함되는 경우를 위함
        Arrays.sort(arr, (a, b) -> {
            if(a.left != b.left) return a.left - b.left;
            return b.right - a.right;
        });
        for(int i=0; i<n; i++) {
            int curL = arr[i].left;
            int curR = arr[i].right;
            // stack의 top에는 이전 구간의 맨 오른쪽 right가 있다.
            // 이전 구간이 끝난 경우 pop
            while(!stack.isEmpty() && stack.peek() < curL) stack.pop();
            if(!stack.isEmpty()){
                int prevR = stack.peek();
                // 부분 겹침 or 접촉
                if(curL <= prevR && curR >= prevR) {
                    System.out.println("NO");
                    return;
                }
            }
            stack.push(curR);
        }
        System.out.println("YES");
    }

    static class Circle {
        int left; int right;
        Circle(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}