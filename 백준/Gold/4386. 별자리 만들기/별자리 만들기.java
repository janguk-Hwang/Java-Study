import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] parent;
    static double[][] point;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        parent = new int[n+1];
        point = new double[n+1][2];
        for(int i=1; i<=n; i++) parent[i] = i;
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            point[i][0] = Double.parseDouble(st.nextToken()); // x 좌표
            point[i][1] = Double.parseDouble(st.nextToken()); // y 좌표
        }
        for(int i=1; i<=n; i++){
            for(int j=i+1; j<=n; j++){
                double dx = point[i][0] - point[j][0];
                double dy = point[i][1] - point[j][1];
                double distance = Math.sqrt(dx*dx + dy*dy);
                pq.offer(new Node(i, j, distance));
            }
        }
        int edgeCnt = 0;
        double total = 0.0;
        while(edgeCnt < n-1){
            Node now = pq.poll();
            int from = now.from;
            int to = now.to;
            if(find(from) != find(to)){
                union(from, to);
                total += now.cost;
                edgeCnt++;
            }
        }
        System.out.printf("%.2f", total);
    }

    public static void union(int a, int b){
        a = find(a);
        b = find(b);
        parent[b] = a;
    }

    public static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    public static class Node implements Comparable<Node>{
        int from;
        int to;
        double cost;
        public Node(int from, int to, double cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o){
            return Double.compare(this.cost, o.cost);
        }
    }
}