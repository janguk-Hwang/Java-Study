import java.io.*;
import java.util.*;

// 연결되지 않은 무게제한이 큰 도로부터 연결한다.
// 출발점과 도착점이 연결될 때 해당 간선의 무게 제한이 출발점과 도착점을 연결하는 도로 중 가장 무게제한이 작은 간선이다.
public class Main {
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int[] parent;
    static int n, m;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        for(int i=1; i<=n; i++) parent[i] = i;
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Node(s, e, c));
        }
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(find(cur.from) != find(cur.to)){
                union(cur.from, cur.to);
                // 출발점과 도착점이 연결되었으면
                if(find(start) == find(end)){
                    System.out.print(cur.cost);
                    return;
                }
            }
        }
        System.out.print(0);
    }

    static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b){
        a = find(a); b = find(b);
        parent[b] = a;
    }

    static class Node implements Comparable<Node>{
        int from; int to; int cost;
        Node(int from, int to, int cost){
            this.from = from; this.to = to; this.cost = cost;
        }
        @Override
        public int compareTo(Node o){
            return Integer.compare(o.cost, this.cost);
        }
    }
}