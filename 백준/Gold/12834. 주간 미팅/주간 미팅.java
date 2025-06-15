import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Node>[] adj;
    static int n, v, e, a, b;
    static int[] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        adj = new ArrayList[v+1];
        for(int i=0; i<=v; i++) adj[i] = new ArrayList<>();
        arr = new int[n];
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adj[start].add(new Node(cost, end));
            adj[end].add(new Node(cost, start));
        }
        int result = 0;
        for(int i=0; i<arr.length; i++) result += dijkstra(arr[i]);
        System.out.print(result);
    }

    public static int dijkstra(int start){
        int[] dist = new int[v+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, start));
        dist[start] = 0;
        while(!pq.isEmpty()){
            Node now = pq.poll();
            int nowCost = now.cost;
            int nowDest = now.dest;
            if(dist[nowDest] < nowCost) continue;
            for(Node next : adj[nowDest]){
                if(dist[next.dest] > nowCost + next.cost){
                    dist[next.dest] = nowCost + next.cost;
                    pq.offer(new Node(dist[next.dest], next.dest));
                }
            }
        }
        return dist[a] + dist[b];
    }

    public static class Node implements Comparable<Node>{
        int cost; int dest;
        public Node(int cost, int dest){
            this.cost = cost;
            this.dest = dest;
        }
        @Override
        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }
}