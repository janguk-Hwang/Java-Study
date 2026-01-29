import java.util.*;
import java.io.*;

public class Main {
    static int n, m, x, y, z;
    static int INF = Integer.MAX_VALUE;
    static int[] dist;
    static ArrayList<Node>[] adj;
    static PriorityQueue<Node> pq;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n + 1];
        for(int i=0; i<=n; i++) adj[i] = new ArrayList<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[u].add(new Node(v, w));
        }
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        z = Integer.parseInt(st.nextToken());
        int[] xToy = dijkstra(x, false);
        int[] yToz = dijkstra(y, false);
        int[] xToz = dijkstra(x, true);
        int rst = -1;
        if(xToy[y] < INF && yToz[z] < INF) rst = xToy[y] + yToz[z];
        System.out.print(rst + " ");
        System.out.print((xToz[z] < INF) ? xToz[z] : -1);
    }

    static int[] dijkstra(int start, boolean flag){
        pq = new PriorityQueue<>();
        dist = new int[n + 1];
        Arrays.fill(dist, INF);
        pq.offer(new Node(start, 0));
        dist[start] = 0;
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cur.cost > dist[cur.to]) continue;
            if(flag && cur.to == y) continue;
            for(Node next : adj[cur.to]){
                if(flag && next.to == y) continue;
                if(cur.cost + next.cost < dist[next.to]){
                    dist[next.to] = cur.cost + next.cost;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }
        return dist;
    }

    static class Node implements Comparable<Node>{
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