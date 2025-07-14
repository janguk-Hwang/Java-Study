import java.io.*;
import java.util.*;

// 임의의 정점에서 가장 멀리 떨어진 정점을 찾고 해당 정점에서 한 번더 가장 멀리 떨어진 정점을 찾아서 트리의 지름을 알아낸다.
public class Main {
    static ArrayList<Node>[] adj;
    static int n;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        if(n == 1){
            System.out.print(0);
            return;
        }
        adj = new ArrayList[n+1];
        for(int i=1; i<=n; i++) adj[i] = new ArrayList<>();
        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[a].add(new Node(b, c));
            adj[b].add(new Node(a, c));
        }
        System.out.print(diameter(diameter(1)[1])[0]);
    }

    public static int[] diameter(int start){
        int dist = Integer.MIN_VALUE;
        int[] temp = new int[2];
        boolean[] visited = new boolean[n+1];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{start, 0});
        visited[start] = true;
        while(!q.isEmpty()){
            int[] now = q.poll();
            for(Node next : adj[now[0]]){
                if(!visited[next.dest]){
                    if(dist < now[1] + next.cost){
                        dist = Math.max(dist, now[1] + next.cost);
                        temp[0] = dist;
                        temp[1] = next.dest;
                    }
                    visited[next.dest] = true;
                    q.offer(new int[]{next.dest, now[1] + next.cost});
                }
            }
        }
        return temp;
    }

    public static class Node{
        int dest; int cost;
        public Node(int dest, int cost){
            this.dest = dest; this.cost = cost;
        }
    }
}