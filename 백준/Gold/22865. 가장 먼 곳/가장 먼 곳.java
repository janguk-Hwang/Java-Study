import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int INF = Integer.MAX_VALUE;
    static int[] location;
    static int[][] dist;
    static ArrayList<Node>[] adj;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        adj = new ArrayList[n+1];
        dist = new int[n+1][3];
        for(int i=0; i<=n; i++) Arrays.fill(dist[i], INF);
        location = new int[3];
        for(int i=0; i<=n; i++) adj[i] = new ArrayList<>();
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<3; i++) location[i] = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[s].add(new Node(c, e));
            adj[e].add(new Node(c, s));
        }
        for(int i=0; i<3; i++) dijkstra(i, location[i]);
        int result = -1;
        int maxOfMin = -1;
        for(int i=1; i<=n; i++){
            // i번째 집에서의 가장 가까운 친구의 집까지의 거리
            int min = Math.min(Math.min(dist[i][0], dist[i][1]), dist[i][2]);
            if(min > maxOfMin){
                maxOfMin = min;
                result = i;
            }
        }
        System.out.print(result);
    }

    public static void dijkstra(int index, int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, start));
        dist[start][index] = 0;
        while(!pq.isEmpty()){
            Node now = pq.poll();
            if(dist[now.dest][index] < now.cost) continue;
            for(Node next : adj[now.dest]){
                int newCost = now.cost + next.cost;
                if(dist[next.dest][index] > newCost){
                    dist[next.dest][index] = newCost;
                    pq.offer(new Node(newCost, next.dest));
                }
            }
        }
    }

    public static class Node implements Comparable<Node>{
        int cost; int dest;
        public Node(int cost, int dest){
            this.cost = cost; this.dest = dest;
        }
        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }
}