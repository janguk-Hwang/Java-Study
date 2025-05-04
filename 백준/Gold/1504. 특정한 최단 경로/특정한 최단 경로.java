import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int INF = Integer.MAX_VALUE;
    static ArrayList<Node>[] adj;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n + 1];
        for(int i=0; i<n+1; i++) adj[i] = new ArrayList<>();

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[s].add(new Node(c, e));
            adj[e].add(new Node(c, s));
        }

        st = new StringTokenizer(br.readLine(), " ");
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        long route1 = dijkstra(1, v1);
        route1 += dijkstra(v1, v2);
        route1 += dijkstra(v2, n);
        long route2 = dijkstra(1, v2);
        route2 += dijkstra(v2, v1);
        route2 += dijkstra(v1, n);

        // 둘 중 최솟값 선택, INF 이상이면 불가능한 경로
        long result = Math.min(route1, route2);
        System.out.println(result >= INF ? -1 : result);
    }

    public static int dijkstra(int startNode, int endNode){
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, startNode));
        dist[startNode] = 0;
        while(!pq.isEmpty()){
            Node now = pq.poll();
            int nc = now.cost;
            int nd = now.dest;
            if(dist[nd] < nc) continue;

            for(Node next : adj[nd]){
                int nextCost = next.cost;
                int nextDest = next.dest;
                if(dist[nextDest] > nc + nextCost){
                    dist[nextDest] = nc + nextCost;
                    pq.offer(new Node(nc + nextCost, nextDest));
                }
            }
        }
        return dist[endNode];
    }

    static class Node implements Comparable<Node>{
        int cost;
        int dest;

        public Node(int dist, int destination){
            this.cost = dist;
            this.dest = destination;
        }

        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }
}
