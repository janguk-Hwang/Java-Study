import java.io.*;
import java.util.*;

// mst는 여러 개가 만들어 질 수 있다.
// 그래서 mst를 구한다고 해서 p->q 간선이 mst에 항상 포함되는 것은 아니다.
// p->q보다 작은 비용의 간선들로 mst가 만들어지는지 확인해본다.
// w_pq보다 작은 간선들로 mst가 구성되지 않고, p와 q가 연결되어 있지 않은 상태면 YES를 출력하고 w_pq보다 작은 간선들로 mst가 구성되면 NO 출력한다
public class Main {
    static PriorityQueue<Node> pq;
    static int[] parent;
    static int t, n, m, p, q;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            pq = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());
            parent = new int[n + 1];
            for(int i=1; i<=n; i++) parent[i] = i;
            int ptoqCost = 0;
            for(int i=0; i<m; i++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                if(s == p && e == q || s == q && e == p) ptoqCost = c;
                pq.add(new Node(s, e, c));
            }
            // p-q보다 비용이 작은 간선들만 union
            while (!pq.isEmpty() && pq.peek().cost < ptoqCost) {
                Node cur = pq.poll();
                union(cur.from, cur.to);
            }
            // p, q가 연결되어 있으면 no
            if(find(p) == find(q)) sb.append("NO").append("\n");
            // p-q보다 비용이 작은 간선들만 union했는데 p-q가 연결되어 있지 않으면 p-q를 연결하여 mst를 구성할 수 있다.
            else sb.append("YES").append("\n");
        }
        System.out.print(sb);
    }

    static class Node implements Comparable<Node>{
        int from; int to; int cost;
        Node(int from, int to, int cost){
            this.from = from; this.to = to; this.cost = cost;
        }
        @Override
        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }

    static void union(int a, int b){
        a = find(a); b = find(b);
        parent[b] = a;
    }

    static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }
}