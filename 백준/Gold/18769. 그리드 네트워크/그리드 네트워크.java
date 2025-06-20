import java.io.*;
import java.util.*;

public class Main {
    static int t;
    static int[] parent;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            PriorityQueue<Node> pq = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            parent = new int[r * c];
            for(int i=0; i<r*c; i++) parent[i] = i;
            for(int i=0; i<r; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<c-1; j++){
                    int cost = Integer.parseInt(st.nextToken());
                    int from = i * c + j;
                    int to = i * c + j + 1;
                    pq.add(new Node(from, to, cost));
                }
            }
            for(int i=0; i<r-1; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<c; j++){
                    int cost = Integer.parseInt(st.nextToken());
                    int from = i * c + j;
                    int to = (i + 1) * c + j;
                    pq.add(new Node(from, to, cost));
                }
            }
            int total = 0;
            int edgeCnt = 0;
            while(!pq.isEmpty()){
                Node now = pq.poll();
                if(find(now.from) != find(now.to)){
                    union(now.from, now.to);
                    total += now.cost;
                    edgeCnt++;
                    if(edgeCnt == r * c - 1) break;
                }
            }
            sb.append(total).append("\n");
        }
        sb.setLength(sb.length()-1);
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