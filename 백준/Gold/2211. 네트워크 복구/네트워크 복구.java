import java.io.*;
import java.util.*;

// 일단 1번 노드에서 나머지 노드까지의 최단거리를 알아내기 위해 다익스트라 실행하고
public class Main {
    static int INF = Integer.MAX_VALUE;
    static int n, m;
    static int[] dist, pre;
    static ArrayList<Node>[] adj;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dist = new int[n + 1];
        pre = new int[n + 1];
        adj = new ArrayList[n + 1];
        for(int i=1; i<=n; i++) adj[i] = new ArrayList<>();
        Arrays.fill(dist, INF);
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[s].add(new Node(e, c));
            adj[e].add(new Node(s, c));
        }
        dijkstra();
        sb.append(n-1).append("\n");
        for(int i=2; i<=n; i++) sb.append(i).append(" ").append(pre[i]).append("\n");
        System.out.print(sb);
    }

    public static void dijkstra(){
        pq.offer(new Node(1, 0));
        dist[1] = 0;
        while(!pq.isEmpty()){
            Node now = pq.poll();
            if(dist[now.dest] < now.cost) continue;
            for(Node next : adj[now.dest]){
                if(dist[next.dest] > now.cost + next.cost){
                    dist[next.dest] = now.cost + next.cost;
                    pre[next.dest] = now.dest;
                    pq.offer(new Node(next.dest, dist[next.dest]));
                }
            }
        }
    }

    public static class Node implements Comparable<Node>{
        int dest; int cost;
        public Node(int dest, int cost){
            this.dest = dest; this.cost = cost;
        }
        @Override
        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }
}