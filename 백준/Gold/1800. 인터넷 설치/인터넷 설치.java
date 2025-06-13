import java.io.*;
import java.util.*;

public class Main {
    static int n, p, k;
    static ArrayList<Node>[] adj;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n+1];
        for(int i=0; i<=n; i++) adj[i] = new ArrayList<>();
        int max = 0;
        for(int i=0; i<p; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[s].add(new Node(c, e));
            adj[e].add(new Node(c, s));
            max = Math.max(max, c);
        }

        int answer = -1;
        int start = -1; int end = max;
        while(start + 1 < end){
            int mid = (start + end) / 2;
            if(dijkstra(mid)){
                end = mid;
                answer = mid;
            }
            else start = mid;
        }
        System.out.print(answer);
    }

    public static boolean dijkstra(int mid){
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        pq.offer(new int[]{0, 1});
        dist[1] = 0;
        while(!pq.isEmpty()){
            int[] now = pq.poll();
            int nowCost = now[0];
            int nowDest = now[1];
            if(dist[nowDest] < nowCost) continue;
            for(Node next : adj[nowDest]){
                int nextDest = next.dest;
                int edgeCnt = (next.cost > mid) ? 1 : 0;
                if(dist[nextDest] > nowCost + edgeCnt){
                    dist[nextDest] = nowCost + edgeCnt;
                    pq.offer(new int[]{dist[nextDest], nextDest});
                }
            }
        }
        return dist[n] <= k;
    }

    public static class Node implements Comparable<Node>{
        int cost; int dest;
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