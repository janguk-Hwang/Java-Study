import java.util.*;
import java.io.*;

// 최소 비용을 갖는 경로에 포함되어있는 도시의 개수, 경로를 방문하는 도시 순서대로 출력
// 이전 노드를 기억해야한다.
public class Main {
    static int n, m;
    static int startNode, endNode;
    static int[] d;
    static int INF = Integer.MAX_VALUE;
    static ArrayList<Node>[] adj;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int[] pre;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        d = new int[n + 1];
        adj = new ArrayList[n + 1];
        pre = new int[n + 1];

        for(int i=0; i<n+1; i++){
            adj[i] = new ArrayList<>();
        }
        Arrays.fill(d, INF);
        Arrays.fill(pre, -1);

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[s].add(new Node(w, e));
        }

        st = new StringTokenizer(br.readLine());
        int startNode = Integer.parseInt(st.nextToken());
        int endNode = Integer.parseInt(st.nextToken());
        dijkstra(startNode);

        List<Integer> route = new ArrayList<>();
        int i = endNode;
        while (i != -1) {
            route.add(i);
            i = pre[i];
        }
        Collections.reverse(route);

        sb.append(d[endNode]).append("\n").append(route.size()).append("\n");
        for(int j=0; j<route.size(); j++){
            sb.append(route.get(j)).append(" ");
        }
        System.out.println(sb);
    }

    static void dijkstra(int startNode){
        pq.offer(new Node(0, startNode));
        d[startNode] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();
            int cost = now.cost;
            int dest = now.dest;

            if(d[dest] < cost){
                continue;
            }

            for(Node next : adj[dest]){
                int nextDistance = next.cost;
                int nextNode = next.dest;

                if(d[nextNode] > nextDistance + cost){
                    d[nextNode] = nextDistance + cost;
                    pre[nextNode] = dest;   // 이전 노드 기억
                    pq.offer(new Node(d[nextNode], nextNode));
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
        int cost;
        int dest;

        public Node(int cost, int dest){
            this.cost = cost;
            this.dest = dest;
        }

        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }
}
