import java.io.*;
import java.util.*;

// 두 로봇이 통신하려면 각각이 위치한 방이 인접해야 한다.
public class Main {
    static int INF = Integer.MAX_VALUE;
    static int n, robot1, robot2;
    static ArrayList<Node>[] adj;
    static int[] dist, prev;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        robot1 = Integer.parseInt(st.nextToken());
        robot2 = Integer.parseInt(st.nextToken());
        dist = new int[n + 1];
        Arrays.fill(dist, INF);
        prev = new int[n + 1];
        Arrays.fill(prev, -1);
        adj = new ArrayList[n + 1];
        for(int i=1; i<=n; i++) adj[i] = new ArrayList<>();
        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[s].add(new Node(e, c));
            adj[e].add(new Node(s, c));
        }
        dijkstra();
        int now = robot2;
        int max = 0;
        while(prev[now] != -1){
            for(Node n : adj[now]){
                if(n.dest == prev[now]){
                    max = Math.max(max, n.cost);
                    break;
                }
            }
            now = prev[now];
        }
        System.out.print(dist[robot2] - max);
    }

    static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[robot1] = 0;
        pq.add(new Node(robot1, 0));
        while(!pq.isEmpty()){
            Node now = pq.poll();
            if(dist[now.dest] < now.cost) continue;
            for(Node next : adj[now.dest]){
                if(dist[next.dest] > now.cost + next.cost){
                    dist[next.dest] = now.cost + next.cost;
                    prev[next.dest] = now.dest;
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