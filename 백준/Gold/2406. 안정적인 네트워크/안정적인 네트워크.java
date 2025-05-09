import java.util.*;
import java.io.*;

// 1번이랑은 필요가 없음
public class Main {
    static int n, m;
    static int[] parent;
    static ArrayList<Node> haveToConnect = new ArrayList<>();
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        for(int i=1; i<=n; i++) parent[i] = i;
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            union(x, y);    // x와 y는 이미 연결된 상태이므로 union
        }

        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<=n; j++){
                int c = Integer.parseInt(st.nextToken());
                if(i < j && i != 1 && j != 1){
                    pq.add(new Node(i, j, c));
                }
            }
        }

        int sum = 0;
        while(!pq.isEmpty()){
            Node now = pq.poll();
            int from = now.from;
            int to = now.to;
            int cost = now.cost;

            if(find(from) != find(to)){
                sum += cost;
                union(from, to);
                haveToConnect.add(now);
            }
        }
        // 최소 비용: sum
        // 연결할 컴퓨터들의 쌍의 개수: haveToConnect의 size
        // 연결할 컴퓨터들의 번호: haveToConnect에 있는 Node 객체의 from, cost
        sb.append(sum).append(" ").append(haveToConnect.size()).append("\n");
        for(Node node : haveToConnect){
            sb.append(node.from).append(" ").append(node.to).append("\n");
        }
        System.out.print(sb);
    }

    public static int find(int a){
        if(a == parent[a]) return a;
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