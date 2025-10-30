import java.io.*;
import java.util.*;

// A: [3 5 7 2 4 6 7 2 5]
// F(A2): 5가 수열 A에서 등장한 횟수
// Ai의 오등큰수는 오른쪽에 있으면서 수열 A에서 등장한 횟수가 F(Ai)보다 큰 수 중에서 가장 왼쪽에 있는 수
// 오른쪽부터 왼쪽으로 이동하면서 스택에서 자신보다 작거나 같은 요소들은 제거하고 출현 횟수가 큰 요소를 만나면 해당 값이 현재 수의 NGF()이다.
// 단조 스택
public class Main {
    static int n;
    static int[] arr, NGF, cnt;
    static HashMap<Integer, Integer> map = new HashMap<>();
    static Stack<Integer> stack = new Stack<>();
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1]; NGF = new int[n + 1]; cnt = new int[1_000_001];
        Arrays.fill(NGF, -1);
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            cnt[arr[i]]++;
        }
        for(int i=n-1; i>=0; i--){
            while(!stack.isEmpty() && cnt[arr[stack.peek()]] <= cnt[arr[i]]){
                stack.pop();
            }
            if(!stack.isEmpty()) NGF[i] = arr[stack.peek()];
            stack.push(i);
        }
        for(int i=0; i<n; i++) sb.append(NGF[i]).append(" ");
        System.out.print(sb);
    }
}