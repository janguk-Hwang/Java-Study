import java.io.*;
import java.util.*;

// 지금까지 나온 수 중에서 중간값을 말해야 한다. -> 지금까지 나온 수가 정렬되어 있어야 중간값을 찾을 수 있다.
// 매번 숫자가 추가될 때마다 정렬을 할 수는 없음. 1 <= N <= 100_000
// 정렬하지 않아도 중간값을 빠르게 찾을 수 있는 방법은 없나?
// 외치는 숫자가 중복이 있나? 말이 없으니까 있다고 생각해야 할 듯
// 외치는 숫자를 입력받을 때, 기준있게 위치시키면 이것이 정렬과 같다.
// 그렇다면 기준은 어떻게 설정해야 할까? 기존 중간값보다 작으면 왼쪽에 크면 오른쪽에 저장한다.
// 여기서도 배열에 저장하는 것보다 우선순위 큐에 넣으면 자동으로 정렬되니까 중간값 기준으로 좌우에 우선순위 큐를 하나씩 둔다.
// 외치는 숫자가 기존 중간값보다 작으면 왼쪽 우선순위 큐에 삽입, 크면 오른쪽 우선순위 큐에 삽입
// 만약 중간값과 같은 값이 외쳐졌고 짝수개면? 어디에 넣어도 상관없다.
// 홀수인 경우에 중간값은 leftPq.peek(), 짝수인 경우에 중간값도 leftPq.peek()
public class Main {
    static int n;
    static PriorityQueue<Integer> leftPq = new PriorityQueue<>(Collections.reverseOrder());
    static PriorityQueue<Integer> rightPq = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            int temp = Integer.parseInt(br.readLine());     // 외쳐진 수
            // 중간값보다 작거나 같은 값은 leftPq에 삽입
            // 우선순위 큐가 비어있으면 peek()를 하지 않고 삽입
            if(leftPq.isEmpty() || temp <= leftPq.peek()){
                leftPq.offer(temp);
            }
            else rightPq.offer(temp);

            if(leftPq.size() > rightPq.size() + 1){
                rightPq.offer(leftPq.poll());
            }
            if(rightPq.size() > leftPq.size()){
                leftPq.offer(rightPq.poll());
            }
            sb.append(leftPq.peek()).append("\n");
        }
        System.out.print(sb);
    }
}