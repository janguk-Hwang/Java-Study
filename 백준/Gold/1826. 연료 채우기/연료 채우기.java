import java.io.*;
import java.util.*;

// 연료 탱크의 제한이 없다 -> 언제 멈추는 것이 중요한 것이 아니라 도달 가능한 경로 중 가장 많은 연료를 주유할 수 있는 주유소를 선택하면 된다.
// 우선순위 큐에 주유 가능량을 저장, 연료가 부족할 때마다 맨 앞의 주유 가능량을 주유
// 주행하는 데에 사용되는 연료는 고려하지 않는 것 같다. 새나가는 것에 포함되었다고 생각.
public class Main {
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    static int n, l, p;
    static int[][] matrix;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        n = Integer.parseInt(br.readLine());
        matrix = new int[n][2];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            matrix[i][0] = a;   // 거리
            matrix[i][1] = b;   // 주유량
        }
        st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        // 거리를 기준으로 정렬
        Arrays.sort(matrix, (a, b) -> a[0] - b[0]);
        int idx = 0;
        int rst = 0;
        while(p < l){
            // 현재 연료로 도달할 수 있는 주유소들의 주유량을 우선순위 큐에 저장
            while(idx < n && matrix[idx][0] <= p){
                pq.offer(matrix[idx][1]);
                idx++;
            }
            // 아직 l에 도달하지 못했는데 우선순위 큐가 비었다면 도달할 수 없으므로 -1 출력
            if(pq.isEmpty()){
                System.out.print(-1);
                return;
            }
            p += pq.poll();
            rst++;
        }
        System.out.print(rst);
    }
}