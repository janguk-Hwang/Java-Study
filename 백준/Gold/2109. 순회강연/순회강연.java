import java.io.*;
import java.util.*;

// 하루에 최대 한 곳에서만 강연을 할 수 있다.
// 강연료가 큰 순서대로 해당 강연을 가장 늦은 날에 배치해야 함
public class Main {
    static int n, max, sum;
    static boolean[] visited;
    // 강연료 기준으로 내림차순 정렬
    static PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            pq.offer(new int[]{p, d});
            max = Math.max(max, d);
        }
        visited = new boolean[max + 1];
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            // 마감날부터 역순으로 순회
            for(int i=cur[1]; i>=1; i--){
                // 해당 날에 스케줄이 없으면
                if(!visited[i]){
                    visited[i] = true;
                    sum += cur[0];
                    break;
                }
            }
        }
        System.out.print(sum);
    }
}