import java.util.*;
import java.io.*;

public class Main {
    static StringTokenizer st;
    static int n, m;
    static int INF = Integer.MAX_VALUE;
    static ArrayList<Node>[] adj;
    static int[] dist;
    static int startNode, destinationNode;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 노드 개수
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());   // 간선 개수
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
        }

        st = new StringTokenizer(br.readLine());
        startNode = Integer.parseInt(st.nextToken());
        destinationNode = Integer.parseInt(st.nextToken());

        dijkstra();

        System.out.println(dist[destinationNode]);
    }

    // Node 클래스
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

    static void dijkstra(){
        // 시작 노드 추가
        pq.offer(new Node(0 ,startNode));
        dist[startNode] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();
            int distance = now.distance;
            int destination = now.destination;

            if(dist[destination] < distance){
                continue;
            }

            for(Node next : adj[destination]){
                int nextDistance = next.distance;
                int nextNode = next.destination;

                if(dist[nextNode] > nextDistance + distance) {
                    dist[nextNode] = nextDistance + distance;
                    pq.offer(new Node(nextDistance + distance, nextNode));
                }
            }
        }
    }
}
