import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] dist;
    static int INF = Integer.MAX_VALUE;
    static ArrayList<Node>[] adj;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n + 1];
        dist = new int[n + 1];
        for(int i=0; i<n+1; i++) adj[i] = new ArrayList<>();
        Arrays.fill(dist, INF);

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[s].add(new Node(c, e));
            adj[e].add(new Node(c, s));
        }

        st = new StringTokenizer(br.readLine(), " ");
        int passNode1 = Integer.parseInt(st.nextToken());
        int passNode2 = Integer.parseInt(st.nextToken());
        long path1 = 0, path2 = 0;
        // 1 → v1 → v2 → N
        path1 += dijkstra(1, passNode1);
        clear();
        path1 += dijkstra(passNode1, passNode2);
        clear();
        path1 += dijkstra(passNode2, n);
        clear();

        // 1 → v2 → v1 → N
        path2 += dijkstra(1, passNode2);
        clear();
        path2 += dijkstra(passNode2, passNode1);
        clear();
        path2 += dijkstra(passNode1, n);
        clear();
        
        long result = Math.min(path1, path2);
        System.out.println(result >= INF ? -1 : result);
    }

    public static int dijkstra(int startNode, int endNode){
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

    static void clear(){
        Arrays.fill(dist, INF);
        pq.clear();
    }
}
