import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static boolean[] visited;
    static ArrayList<Node>[] adj;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        adj = new ArrayList[n + 1];
        for(int i=1; i<=n; i++) adj[i] = new ArrayList<>();
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            while(true){
                int e = Integer.parseInt(st.nextToken());
                if(e == -1) break;
                int c = Integer.parseInt(st.nextToken());
                adj[s].add(new Node(e, c));
                adj[e].add(new Node(s, c));
            }
        }
        System.out.print(bfs(bfs(1)[0])[1]);
    }

    static int[] bfs(int start){
        int[] farthest = new int[2];    // 노드 번호, 거리
        farthest[0] = start;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start, 0));
        visited = new boolean[n + 1];
        visited[start] = true;
        while(!q.isEmpty()){
            Node cur = q.poll();
            for(Node next : adj[cur.dest]){
                if(!visited[next.dest]){
                    visited[next.dest] = true;
                    if(farthest[1] < cur.cost + next.cost){
                        farthest[0] = next.dest;
                        farthest[1] = cur.cost + next.cost;
                    }
                    q.add(new Node(next.dest, cur.cost + next.cost));
                }
            }
        }
        return farthest;
    }

    static class Node{
        int dest; int cost;
        Node(int dest, int cost){
            this.dest = dest; this.cost = cost;
        }
    }
}