import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int n, m;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        for(int i=0; i<=n; i++) parent[i] = i;
        long sum = 0;
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.offer(new Node(s, e, c));
            sum += c;
        }

        int edgeCnt = 0;
        long total = 0;
        while(!pq.isEmpty()){
            Node now = pq.poll();
            if(find(now.from) != find(now.to)){
                total += now.cost;
                union(now.from, now.to);
                edgeCnt++;
                if(edgeCnt == n-1) break;
            }
        }
        if(edgeCnt != n-1){
            System.out.print(-1);
            return;
        }
        System.out.print(sum - total);
    }

    public static void union(int a, int b){
        a = find(a); b = find(b);
        parent[b] = a;
    }

    public static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    public static class Node implements Comparable<Node>{
        int from; int to; int cost;
        public Node(int from, int to, int cost){
            this.from = from; this.to = to; this.cost = cost;
        }
        @Override
        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }
}