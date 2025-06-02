import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static long INF = Long.MAX_VALUE;
    static List<Node>[] adj;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int[] visible;
    static long[] dist;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dist = new long[n+1];
        visible = new int[n];
        Arrays.fill(dist, INF);
        adj = new ArrayList[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) adj[i] = new ArrayList<>();
        for(int i=0; i<n; i++){
            visible[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if((visible[s] == 1 && s != n-1) || visible[e] == 1 && e != n-1) continue;
            adj[s].add(new Node(e, c));
            adj[e].add(new Node(s, c));
        }

        dist[0] = 0;
        pq.offer(new Node(0, 0));
        while(!pq.isEmpty()){
            Node now = pq.poll();
            int nowDest = now.dest;
            long nowCost = now.cost;
            if(dist[nowDest] < nowCost) continue;
            for(Node next : adj[nowDest]){
                int nextDest = next.dest;
                long nextCost = next.cost;
                if(dist[nextDest] > nowCost + nextCost) {
                    dist[nextDest] = nowCost + nextCost;
                    pq.offer(new Node(nextDest, dist[nextDest]));
                }
            }
        }
        System.out.print(dist[n-1] == INF ? -1 : dist[n-1]);
    }

    public static class Node implements Comparable<Node>{
        int dest;
        long cost;
        public Node(int dest, long cost){
            this.dest = dest;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o){
            return Long.compare(this.cost, o.cost);
        }
    }
}