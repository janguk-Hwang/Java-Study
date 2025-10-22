import java.io.*;
import java.util.*;

// 임의의 두 마을을 이동하는 최단 경로 중 비용이 가장 큰 경로 -> mst에서 양 끝 노드간의 거리
public class Main {
    static boolean[] visited;
    static ArrayList<Node>[] adj;
    static int n, k;
    static int[] parent;
    static PriorityQueue<Nodes> pq = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        parent = new int[n];
        for(int i=0; i<n; i++) parent[i] = i;
        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.offer(new Nodes(s, e, c));
            pq.offer(new Nodes(e, s, c));
        }
        adj = new ArrayList[n];
        for(int i=0; i<n; i++) adj[i] = new ArrayList<>();
        int sum = 0;
        while(!pq.isEmpty()){
            Nodes cur = pq.poll();
            if(find(cur.from) != find(cur.to)){
                union(cur.from, cur.to);
                sum += cur.cost;
                adj[cur.from].add(new Node(cur.to, cur.cost));
                adj[cur.to].add(new Node(cur.from, cur.cost));
            }
        }
        sb.append(sum).append("\n");
        sb.append(bfs(bfs(1)[0])[1]);
        System.out.print(sb);
    }

    static int[] bfs(int start){
        int[] farthest = new int[2];
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start, 0));
        visited = new boolean[n];
        visited[start] = true;
        while(!q.isEmpty()){
            Node cur = q.poll();
            for(Node next : adj[cur.dest]){
                if(!visited[next.dest]){
                    visited[next.dest] = true;
                    if(farthest[1] < next.cost + cur.cost){
                        farthest[1] = next.cost + cur.cost;
                        farthest[0] = next.dest;
                    }
                    q.add(new Node(next.dest, cur.cost + next.cost));
                }
            }
        }
        return farthest;
    }

    static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b){
        a = find(a); b = find(b);
        parent[b] = a;
    }

    static class Nodes implements Comparable<Nodes>{
        int from; int to; int cost;
        Nodes(int from, int to, int cost){
            this.from = from; this.to = to; this.cost = cost;
        }
        @Override
        public int compareTo(Nodes o){
            return Integer.compare(this.cost, o.cost);
        }
    }

    static class Node{
        int dest; int cost;
        Node(int dest, int cost){
            this.dest = dest; this.cost = cost;
        }
    }
}