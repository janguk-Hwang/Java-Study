import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] parent, sex;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        for(int i=1; i<=n; i++) parent[i]= i;
        sex = new int[n+1];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=n; i++){        // 남자: 0, 여자: 1
            if(st.nextToken().equals("M")) sex[i] = 0;
            else sex[i] = 1;
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.offer(new Node(s, e, c));
        }

        List<Node> list = new ArrayList<>();
        int total = 0;
        while(!pq.isEmpty()){
            Node now = pq.poll();
            int from = now.from;
            int to = now.to;
            int cost = now.cost;

            if(find(from) != find(to) && sex[from] != sex[to]){
                union(from, to);
                list.add(now);
                total += cost;
            }
        }
        if(list.size() != n-1) System.out.print(-1);
        else System.out.println(total);
    }

    public static int find(int a){
        if(parent[a] == a) return parent[a];
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