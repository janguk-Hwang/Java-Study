import java.util.*;
import java.io.*;

public class Main {
    static StringTokenizer st;
    static int v, e, k;
    static int[] d;
    static int INF = Integer.MAX_VALUE;
    static ArrayList<Node>[] adj;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        adj = new ArrayList[v + 1];
        d = new int[v + 1];

        for(int i=0; i<v+1; i++){
            adj[i] = new ArrayList<>();
        }
        Arrays.fill(d, INF);
        for(int j=0; j<e; j++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[s].add(new Node(w, e));
        }

        dijkstra();

        for(int i=1; i<=v; i++){
            if(d[i] == Integer.MAX_VALUE) sb.append("INF").append("\n");
            else sb.append(d[i]).append("\n");
        }
        System.out.println(sb);
    }

    static class Node implements Comparable<Node>{
        int weight;
        int dest;

        public Node(int distance, int destination){
            this.weight = distance;
            this.dest = destination;
        }

        public int compareTo(Node o){
            return Integer.compare(this.weight, o.weight);
        }
    }

    static void dijkstra(){
        pq.offer(new Node(0, k));
        d[k] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();
            int dist = now.weight;
            int dest = now.dest;

            if(d[dest] < dist){
                continue;
            }

            for(Node next : adj[dest]){
                int nextDistance = next.weight;
                int nextNode = next.dest;

                if(d[nextNode] > nextDistance + dist) {
                    d[nextNode] = nextDistance + dist;
                    pq.offer(new Node(nextDistance + dist, nextNode));
                }
            }
        }
    }
}
