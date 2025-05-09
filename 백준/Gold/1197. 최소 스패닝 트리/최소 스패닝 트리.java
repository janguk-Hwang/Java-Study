import java.util.*;
import java.io.*;

public class Main {
    static int v, e;
    static int[] parent;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        parent = new int[v + 1];
        for(int i=1; i<=v; i++) parent[i] = i;
        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Node(s, e, c));
        }

        int edgeCnt = 0;
        int sum = 0;
        while(edgeCnt < v-1){
            Node now = pq.poll();
            int from = now.from;
            int to = now.to;
            int cost = now.cost;

            if(find(from) != find(to)){
                sum += now.cost;
                union(from, to);
                edgeCnt++;
            }
        }
        System.out.print(sum);
    }

    public static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b){
        a = find(a);
        b = find(b);
        parent[b] = a;
    }

    static class Node implements Comparable<Node>{
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