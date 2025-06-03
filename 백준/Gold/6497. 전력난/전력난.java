import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        while(true){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if(m == 0 && n == 0) break;
            parent = new int[n+1];
            for(int i=0; i<=n; i++) parent[i] = i;
            PriorityQueue<Node> pq = new PriorityQueue<>();
            int originTotal = 0;
            for(int i=0; i<m; i++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                pq.offer(new Node(s, e, c));
                originTotal += c;
            }
            int connectTotal = 0;
            while(!pq.isEmpty()){
                Node now = pq.poll();
                int a = find(now.from);
                int b = find(now.to);
                if(a != b){
                    union(a, b);
                    connectTotal += now.cost;
                }
            }
            sb.append(originTotal - connectTotal).append("\n");
        }
        System.out.print(sb);
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
            return Integer.compare(this.cost, o.cost);
        }
    }
}