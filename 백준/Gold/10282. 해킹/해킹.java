import java.io.*;
import java.util.*;

public class Main {
    static int t, n, d, c;
    static int INF = Integer.MAX_VALUE;
    static ArrayList<Node>[] adj;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        for(int p=0; p<t; p++){
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            adj = new ArrayList[n+1];
            for(int i=1; i<=n; i++) adj[i] = new ArrayList<>();
            for(int i=0; i<d; i++){
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                adj[b].add(new Node(s, a));
            }
            int[] result = dijkstra(c);
            int zombie = 0;
            int max = 0;
            for(int i=1; i<result.length; i++) {
                if(result[i] != INF) {
                    zombie++;
                    max = Math.max(max, result[i]);
                }
            }
            sb.append(zombie).append(" ").append(max).append("\n");
        }
        System.out.print(sb);
    }

    public static int[] dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        pq.offer(new Node(0, start));
        dist[start] = 0;
        while(!pq.isEmpty()){
            Node now = pq.poll();
            int nowCost = now.cost;
            int nowDest = now.dest;
            if(dist[nowDest] < nowCost) continue;

            for(Node next : adj[nowDest]){
                int nextCost = next.cost;
                int nextDest = next.dest;
                if(dist[nextDest] > nowCost + nextCost){
                    dist[nextDest] = nowCost + nextCost;
                    pq.offer(new Node(dist[nextDest], nextDest));
                }
            }
        }
        return dist;
    }

    public static class Node implements Comparable<Node>{
        int cost;
        int dest;
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