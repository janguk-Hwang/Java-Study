import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] parent;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        for(int i=0; i<=n; i++) parent[i] = i;
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.offer(new Node(a, b, c));
        }
        int edgeCnt = 0;
        int total = 0;
        while(edgeCnt < n-2){
            Node now = pq.poll();
            if(find(now.from) != find(now.to)){
                union(now.from, now.to);
                edgeCnt++;
                total += now.cost;
            }
        }
        System.out.print(total);
    }

    public static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b){
        a = find(a);
        b = find(b);
        parent[b] = a;
    }

    public static class Node implements Comparable<Node>{
        int from;
        int to;
        int cost;
        public Node(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
}