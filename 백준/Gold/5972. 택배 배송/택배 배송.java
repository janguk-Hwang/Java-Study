import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int INF = Integer.MAX_VALUE;
    static ArrayList<Node>[] adj;
    static int[] dist;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n + 1];
        dist = new int[n + 1];
        for(int i=0; i<n+1; i++){
            adj[i] = new ArrayList<>();
            dist[i] = INF;
        }

        for(int j=0; j<m; j++){
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adj[start].add(new Node(weight, end));
            adj[end].add(new Node(weight, start));
        }
        dijkstra();
        System.out.println(dist[n]);
    }

    static void dijkstra(){
        pq.offer(new Node(0 ,1));
        dist[1] = 0;
        while(!pq.isEmpty()){
            Node now = pq.poll();
            int distance = now.distance;
            int dest = now.destination;
            if(dist[dest] < distance) continue;

            for(Node next : adj[dest]){
                int nextDist = next.distance;
                int nextNode = next.destination;
                if(dist[nextNode] > nextDist + distance) {
                    dist[nextNode] = nextDist + distance;
                    pq.offer(new Node(nextDist + distance, nextNode));
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
        int distance;
        int destination;
        public Node(int distance, int destination){
            this.distance = distance;
            this.destination = destination;
        }
        public int compareTo(Node o){
            return Integer.compare(this.distance, o.distance);
        }
    }
}
