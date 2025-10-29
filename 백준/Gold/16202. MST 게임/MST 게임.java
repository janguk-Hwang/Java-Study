import java.io.*;
import java.util.*;

// mst를 구성하는 간선의 비용 합을 구하고 해당 mst를 구성하는 가장 비용이 작은 간선을 빼고
public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int n, m, k;
    static int[] parent;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        for(int i=1; i<=n; i++) parent[i] = i;
        Node[] edges = new Node[m];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges[i] = new Node(a, b, i+1);
        }
        boolean flag = false;
        ArrayList<Node> removed = new ArrayList<>();
        for(int i=0; i<k; i++){
            if(flag){
                sb.append(0).append(" ");
                continue;
            }
            parent = new int[n+1];
            for (int j=1; j<=n; j++) parent[j] = j;
            int sum = 0;
            int edgeCnt = 0;
            Node minEdge = null;
            for(Node e : edges){
                if(removed.contains(e)) continue; // 이미 제거된 간선
                if(find(e.from) != find(e.to)){
                    union(e.from, e.to);
                    sum += e.cost;
                    edgeCnt++;
                    if(minEdge == null || e.cost < minEdge.cost) minEdge = e;
                    if(edgeCnt == n-1) break;
                }
            }
            if(edgeCnt < n-1){
                sb.append(0).append(" ");
                flag = true;
            }
            else{
                sb.append(sum).append(" ");
                removed.add(minEdge);
            }
        }
        System.out.print(sb);
    }

    static void union(int a, int b){
        a = find(a); b = find(b);
        parent[b] = a;
    }

    static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
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
}