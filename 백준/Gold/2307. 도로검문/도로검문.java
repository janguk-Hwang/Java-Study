import java.io.*;
import java.util.*;

public class Main {
    static List<int[]> list = new ArrayList<>();    // 막은 도로의 양 끝 정점을 저장
    static int INF = 50_000_001;
    static int n, m;
    static ArrayList<Node>[] adj;
    static int[] dist, prev;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dist = new int[n + 1];
        Arrays.fill(dist, INF);
        prev = new int[n + 1];
        Arrays.fill(prev, -1);
        adj = new ArrayList[n + 1];
        for(int i=1; i<=n; i++) adj[i] = new ArrayList<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[s].add(new Node(e, c));
            adj[e].add(new Node(s, c));
        }

        dijkstra(1, -1, -1);
        int originMin = dist[n];

        int now = n;
        while(prev[now] != -1){
            int pre = prev[now];
            // 역추적하면서 최소 경로에 포함되는 도로의 양 끝 노드를 저장
            list.add(new int[]{now, pre});
            now = prev[now];
        }
        int max = 0;
        for(int[] temp : list){
            dist = new int[n + 1];
            Arrays.fill(dist, INF);
            dijkstra(1, temp[1], temp[0]);
            if(dist[n] == INF){
                System.out.print(-1);
                return;
            }
            max = Math.max(max, dist[n]);
        }
        System.out.print(max - originMin);
    }
    

    static void dijkstra(int start, int banS, int banE){
        pq.offer(new Node(start, 0));
        dist[start] = 0;
        while(!pq.isEmpty()){
            Node now = pq.poll();
            if(dist[now.dest] < now.cost) continue;
            for(Node next : adj[now.dest]){
                if(dist[next.dest] > now.cost + next.cost){
                    if(now.dest == banS && next.dest == banE || next.dest == banS && now.dest == banE) continue;
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