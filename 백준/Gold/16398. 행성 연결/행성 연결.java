import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] parent;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        parent = new int[n];
        for(int i=0; i<n; i++) parent[i] = i;
        for(int i=0; i<n; i++){
            st= new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                int temp = Integer.parseInt(st.nextToken());
                if(i < j) pq.offer(new Node(i, j, temp));
            }
        }
        int edgeCnt = 0;
        long total = 0;
        while(!pq.isEmpty()){
            Node now = pq.poll();
            int a = now.fr;
            int b = now.to;
            int c = now.co;
            if(find(a) != find(b)){
                union(a, b);
                total += c;
                edgeCnt++;
                if(edgeCnt == n-1) break;
            }
        }
        System.out.print(total);
    }

    public static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b){
        a = find(a); b = find(b);
        parent[b] = a;
    }

    public static class Node implements Comparable<Node>{
        int fr; int to; int co;
        public Node(int fr, int to, int co){
            this.fr = fr; this.to = to; this.co = co;
        }
        @Override
        public int compareTo(Node o){
            return Integer.compare(this.co, o.co);
        }
    }
}