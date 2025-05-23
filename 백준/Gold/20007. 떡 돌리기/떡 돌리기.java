import java.io.*;
import java.util.*;

public class Main {
    static int n, m, x, y;
    static int INF = Integer.MAX_VALUE;
    static int[] dist;
    static ArrayList<Node>[] adj;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        dist = new int[n];
        Arrays.fill(dist, INF);
        adj = new ArrayList[n];
        for(int i=0; i<n; i++) adj[i] = new ArrayList<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[s].add(new Node(c, e));
            adj[e].add(new Node(c, s));
        }
        int[] temp = dijkstra(y);
        ArrayList<Integer> distance = new ArrayList<>();
        for(int i=0; i<n; i++){
            if(i == y) continue;
            if(temp[i] == INF || temp[i] * 2 > x){
                System.out.println("-1");
                return;
            }
            distance.add(temp[i] * 2);
        }
        Collections.sort(distance);
        int day = 0;
        int i = 0;
        while(i < distance.size()){
            int oneDayMove = 0;
            while(i < distance.size() && oneDayMove + distance.get(i) <= x){
                oneDayMove += distance.get(i);
                i++;
            }
            day++;
        }
        System.out.print(day);
    }

    public static int[] dijkstra(int startNode){
        pq.offer(new Node(0, startNode));
        dist[startNode] = 0;
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