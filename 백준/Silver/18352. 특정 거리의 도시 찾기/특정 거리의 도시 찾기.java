import java.io.*;
import java.util.*;

public class Main {
    static int INF = Integer.MAX_VALUE;
    static ArrayList<Node>[] adj;
    static int[] dist;
    static int n, m, k, x;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n + 1];
        for(int i=1; i<=n; i++) adj[i] = new ArrayList<>();
        dist = new int[n + 1];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            adj[s].add(new Node(e, 1));
        }
        dijkstra();
        boolean flag = false;
        for(int i=1; i<=n; i++){
            if(dist[i] == k){
                flag = true;
                sb.append(i).append("\n");
            }
        }
        if(!flag){
            System.out.print(-1);
            return;
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }

    static void dijkstra(){
        Arrays.fill(dist, INF);
        pq.add(new Node(x, 0));
        dist[x] = 0;
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(dist[cur.dest] < cur.cost) continue;
            for(Node next : adj[cur.dest]){
                if(dist[next.dest] > cur.cost + next.cost){
                    dist[next.dest] = cur.cost + next.cost;
                    pq.add(new Node(next.dest, dist[next.dest]));
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
        int dest; int cost;
        Node(int dest, int cost){
            this.dest = dest; this.cost = cost;
        }
        @Override
        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }
}