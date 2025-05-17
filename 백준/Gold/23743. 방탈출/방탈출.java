import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] parent, t;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = new int[n+1];
        parent = new int[n+1];
        for(int i=1; i<=n; i++) parent[i] = i;
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.offer(new Node(f, t, c));
        }
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=n; i++){
            t[i] = Integer.parseInt(st.nextToken());
            // 각 방에서 할 수 있는 선택은 해당 방에 비상탈출구를 설치하거나 다른 방과 연결하는 것 중 더 적은 비용이 드는 것을 선택
            // 더 비용이 적은 방법을 선택한다.
            pq.offer(new Node(i, 0, t[i]));     // 0번 정점(비상탈출구 설치)과 해당 방을 잇는 간선을 추가, 어차피 0번~n번 정점까지 연결된다.
        }
        int result = 0;
        while(!pq.isEmpty()){
            Node now = pq.poll();
            int from = now.from;
            int to = now.to;
            int cost = now.cost;

            if(find(from) != find(to)){
                union(from, to);
                result += cost;
            }
        }
        System.out.print(result);
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