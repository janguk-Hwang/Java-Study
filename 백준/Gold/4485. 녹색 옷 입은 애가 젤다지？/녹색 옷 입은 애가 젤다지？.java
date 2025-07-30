import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int n, t, testCaseNum;
    static ArrayList<Node>[] adj;
    static int[] dist;
    static int[][] matrix;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        testCaseNum = 0;
        while((n = Integer.parseInt(br.readLine())) != 0){
            testCaseNum++;
            matrix = new int[n][n];
            adj = new ArrayList[n * n];
            for(int i=0; i<n*n; i++) adj[i] = new ArrayList<>();
            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++) matrix[i][j] = Integer.parseInt(st.nextToken());
            }
            makeAdj();
            dijkstra();
            sb.append("Problem ").append(testCaseNum).append(": ").append(dist[n * n - 1]).append("\n");
        }
        System.out.print(sb);
    }

    public static void makeAdj(){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                for(int d=0; d<4; d++){
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                    adj[i * n + j].add(new Node(nr * n + nc, matrix[nr][nc]));
                }
            }
        }
    }

    public static void dijkstra(){
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(0, matrix[0][0]));
        dist = new int[n * n];
        Arrays.fill(dist, INF);
        dist[0] = matrix[0][0];
        while(!q.isEmpty()){
            Node now = q.poll();
            if(dist[now.dest] < now.cost) continue;
            for(Node next : adj[now.dest]){
                if(dist[next.dest] > now.cost + next.cost){
                    dist[next.dest] = now.cost + next.cost;
                    q.offer(new Node(next.dest, now.cost + next.cost));
                }
            }
        }
    }

    public static class Node implements Comparable<Node>{
        int dest; int cost;
        public Node(int dest, int cost){
            this.dest = dest; this.cost = cost;
        }
        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }
}