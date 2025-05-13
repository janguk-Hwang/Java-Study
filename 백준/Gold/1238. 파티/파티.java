import java.io.*;
import java.util.*;

public class Main {
    static int n, m, x;
    static int INF = Integer.MAX_VALUE;
    static ArrayList<Node>[] adj;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n+1];
        for(int i=0; i<=n; i++) adj[i] = new ArrayList<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[s].add(new Node(c, e));     // 단방향
        }
        int[] result = new int[n+1];
        int max = Integer.MIN_VALUE;
        int[] xToN = dijkstra(x);
        for(int i=1; i<=n; i++){
            int[] nToX = dijkstra(i);
            result[i] = nToX[x] + xToN[i];
            max = Math.max(max, result[i]);
        }
        System.out.print(max);
    }

    static int[] dijkstra(int start){
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, start));
        dist[start] = 0;
        while(!pq.isEmpty()){
            Node now = pq.poll();
            int nowCost = now.cost;
            int nowDest = now.to;
            if(dist[nowDest] < nowCost) continue;
            for(Node next : adj[nowDest]){
                int nextCost = next.cost;
                int nextDest = next.to;
                if(dist[nextDest] > nowCost + nextCost){
                    dist[nextDest] = nowCost + nextCost;
                    pq.offer(new Node(dist[nextDest], nextDest));
                }
            }
        }
        return dist;
    }

    static class Node implements Comparable<Node>{
        int cost;
        int to;
        public Node(int cost, int to){
            this.cost = cost;
            this.to = to;
        }
        @Override
        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
}