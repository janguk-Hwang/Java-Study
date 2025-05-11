import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] parent, wi;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        wi = new int[n + 1];
        parent = new int[n + 1];
        for(int i=0; i<=n; i++) parent[i] = i;
        for(int i=1; i<=n; i++){
            int wuWater = Integer.parseInt(br.readLine());
            pq.add(new Node(0, i, wuWater));
        }
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<=n; j++){
                int c = Integer.parseInt(st.nextToken());
                if(i < j) pq.add(new Node(i, j, c));
            }
        }

        int connection = 0;
        int sum = 0;
        while(connection < n){
            Node now = pq.poll();
            if(find(now.from) != find(now.to)){
                union(now.from, now.to);
                sum += now.cost;
                connection++;
            }
        }
        System.out.println(sum);
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