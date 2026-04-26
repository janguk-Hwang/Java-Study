import java.io.*;
import java.util.*;

// 왼쪽 최대 높이와 오른쪽 최대 높이 중 더 낮은 쪽에 의해 결정
public class Main {
    static Stack<Integer> stack = new Stack<>();
    static int h, w;
    static int[] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        arr = new int[w];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<w; i++) arr[i] = Integer.parseInt(st.nextToken());
        int rst = 0;
        for(int i=0; i<w; i++){
            while(!stack.isEmpty() && arr[i] > arr[stack.peek()]){
                int temp = stack.pop();     // 웅덩이의 가장 낮은 바닥 부분
                if(stack.isEmpty()) break;  // 왼쪽 벽이 없으므로 물이 고일 수 없음
                int left = stack.peek();    // 왼쪽 벽 인덱스
                int width = i - left - 1;
                int height = Math.min(arr[left], arr[i]) - arr[temp];
                rst += width * height;
            }
            stack.push(i);
        }
        System.out.print(rst);
    }
}