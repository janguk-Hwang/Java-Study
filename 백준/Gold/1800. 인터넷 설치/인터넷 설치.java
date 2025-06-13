import java.io.*;
import java.util.*;

public class Main {
    static int n, p, k;
    static ArrayList<Node>[] adj;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n+1];
        for(int i=0; i<=n; i++) adj[i] = new ArrayList<>();
        int max = 0;
        for(int i=0; i<p; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[s].add(new Node(c, e));
            adj[e].add(new Node(c, s));
            max = Math.max(max, c);
        }

        // 결정 문제: 상한선을 mid로 설정했을 때, 1번 컴퓨터에서 n번 컴퓨터까지
        // k개 이하의 비싼 간선(mid보다 비싼 간선)으로 연결이 가능한가?
        // mid보다 비싼 간선이 k개보다 많으면 불가능
        // mid보다 비싼 간선이 k개로 커버가능하면 가능
        // fffffffffffffffttttttttttttttttttttttt
        int answer = -1;
        int start = -1; int end = max;
        while(start + 1 < end){
            int mid = (start + end) / 2;
            if(dijkstra(mid)){      // true가 반환되면 더 작은 mid값으로 탐색해본다.
                end = mid;
                answer = mid;
            }
            else start = mid;
        }
        // 1번과 n번을 잇는 것이 가능하면 다익스트라 함수의 반환값이 true인 mid값이 항상 존재한다.
        // mid == max이면 dist[n]은 0이 된다. k는 최소 0이므로 true를 반환한다.
        // 그러므로 1번과 n번을 이을 수 없으면 answer은 초기값 그대로 -1을 유지한다.
        System.out.print(answer);
    }

    public static boolean dijkstra(int mid){
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        pq.offer(new int[]{0, 1});
        dist[1] = 0;
        while(!pq.isEmpty()){
            int[] now = pq.poll();
            int nowCost = now[0];
            int nowDest = now[1];
            if(dist[nowDest] < nowCost) continue;
            for(Node next : adj[nowDest]){
                int nextDest = next.dest;
                int edgeCnt = (next.cost > mid) ? 1 : 0;
                if(dist[nextDest] > nowCost + edgeCnt){
                    dist[nextDest] = nowCost + edgeCnt;
                    pq.offer(new int[]{dist[nextDest], nextDest});
                }
            }
        }
        // 사용된 mid보다 긴 간선의 수가 k보다 작거나 같은지 여부 반환 -> true이면 mid길이로 연결이 가능
        // 가능하므로 mid를 더 낮춰서 탐색해본다.
        return dist[n] <= k;
    }

    public static class Node implements Comparable<Node>{
        int cost; int dest;
        public Node(int cost, int dest){
            this.cost = cost;
            this.dest = dest;
        }
        @Override
        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
}