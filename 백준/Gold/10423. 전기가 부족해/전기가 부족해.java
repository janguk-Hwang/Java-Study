import java.util.*;
import java.io.*;

public class Main{
    static int n, m, k;
    static int[] parent;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        for(int i=1; i<=n; i++) parent[i] = i;
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<k; i++){
            int temp = Integer.parseInt(st.nextToken());
            parent[temp] = 0;
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.offer(new Node(s, e, c));
        }
        int total = 0;
        while(!pq.isEmpty()){
            Node now = pq.poll();
            int a = find(now.from);
            int b = find(now.to);
            if(a != b){
                union(a, b);
                total += now.cost;
            }
        }
        System.out.print(total);
    }

    public static int find(int a){
        if(parent[a] == 0 || parent[a] == a) return parent[a];
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b){
        if(a == 0 || b == 0) parent[a] = parent[b] = 0;
        else parent[b] = a;
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