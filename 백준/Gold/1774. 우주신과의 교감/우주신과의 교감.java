import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] parent;
    static int[][] arr;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        arr = new int[n+1][2];
        for(int i=1; i<=n; i++){
            parent[i] = i;
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            pq.offer(new Node(a, b, 0));
        }

        for(int i=1; i<=n; i++){
            for(int j=i+1; j<=n; j++){
                double dist = dist(arr[i][0], arr[i][1], arr[j][0], arr[j][1]);
                pq.offer(new Node(i, j, dist));
            }
        }

        double total = 0;
        while(!pq.isEmpty()){
            Node now = pq.poll();
            if(find(now.from) != find(now.to)){
                union(now.from, now.to);
                total += now.cost;
            }
        }
        System.out.printf("%.2f", total);
    }

    public static double dist(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public static void union(int a, int b){
        a = find(a); b = find(b);
        parent[b] = a;
    }

    public static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    public static class Node implements Comparable<Node>{
        int from; int to; double cost;
        public Node(int a, int b, double c){
            this.from = a;
            this.to = b;
            this.cost = c;
        }
        @Override
        public int compareTo(Node o){
            return Double.compare(this.cost, o.cost);
        }
    }
}