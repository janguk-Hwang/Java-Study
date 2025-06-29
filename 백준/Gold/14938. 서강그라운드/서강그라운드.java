import java.io.*;
import java.util.*;

public class Main {
    static int INF = Integer.MAX_VALUE;
    static ArrayList<Node>[] adj;
    static int[] arr;
    static int n, m, r;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        adj = new ArrayList[n + 1];
        for(int i=1; i<=n; i++) adj[i] = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) arr[i] = Integer.parseInt(st.nextToken());
        for(int i=0; i<r; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[s].add(new Node(e, c));
            adj[e].add(new Node(s, c));
        }
        int max = 0;
        for(int i=1; i<=n; i++) max = Math.max(max, dijkstra(i));
        System.out.print(max);
    }

    public static int dijkstra(int start){
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        while(!pq.isEmpty()){
            Node now = pq.poll();
            if(now.cost > dist[now.to]) continue;
            for(Node next : adj[now.to]){
                int newCost = now.cost + next.cost;
                if(newCost < dist[next.to]){
                    dist[next.to] = newCost;
                    pq.offer(new Node(next.to, now.cost + next.cost));
                }
            }
        }
        int total = 0;
        for(int i=1; i<=n; i++) if(dist[i] <= m) total += arr[i];
        return total;
    }

    public static class Node implements Comparable<Node>{
        int to; int cost;
        public Node(int to, int cost){
            this.to = to; this.cost = cost;
        }
        @Override
        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }
}