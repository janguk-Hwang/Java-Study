import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int INF = Integer.MAX_VALUE;
    static ArrayList<Node>[] adj;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n+1];
        for(int i=1; i<=n; i++) adj[i] = new ArrayList<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[a].add(new Node(c, b));
            adj[b].add(new Node(c, a));
        }
        st = new StringTokenizer(br.readLine(), " ");
        int num1 = Integer.parseInt(st.nextToken());
        int num2 = Integer.parseInt(st.nextToken());
        
        System.out.println(dijkstra(num1, num2));
    }

    public static int dijkstra(int start, int end){
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> b.cost - a.cost);
        int[] dist = new int[n+1];
        Arrays.fill(dist, -1);
        pq.offer(new Node(INF, start));
        dist[start] = INF;
        while(!pq.isEmpty()){
            Node now = pq.poll();
            int nowCost = now.cost;
            int nowDest = now.dest;
            if (dist[nowDest] > nowCost) continue;
            if (nowDest == end) return dist[end];
            
            for(Node next : adj[nowDest]){
                int nextCost = Math.min(dist[nowDest], next.cost);
                int nextDest = next.dest;
                if(dist[nextDest] < nextCost){
                    dist[nextDest] = nextCost;
                    pq.offer(new Node(nextCost, nextDest));
                }
            }
        }
        return 0;
    }

    public static class Node{
        int cost;
        int dest;
        public Node(int cost, int dest){
            this.cost = cost;
            this.dest = dest;
        }
    }
}