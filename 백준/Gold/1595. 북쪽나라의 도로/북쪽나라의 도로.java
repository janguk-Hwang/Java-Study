import java.util.*;
import java.io.*;

public class Main {
    static class Edge {
        int to, cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static int n, maxDist, farthestNode;
    static ArrayList<Edge>[] adjList;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        adjList = new ArrayList[10001];
        for (int i = 0; i < 10001; i++) {
            adjList[i] = new ArrayList<>();
        }

        int maxNode = 0;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            StringTokenizer st = new StringTokenizer(line);
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjList[u].add(new Edge(v, cost));
            adjList[v].add(new Edge(u, cost));
            maxNode = Math.max(maxNode, Math.max(u, v));
        }

        visited = new boolean[10001];
        maxDist = 0;
        dfs(1, 0);

        visited = new boolean[10001];
        maxDist = 0;
        dfs(farthestNode, 0);

        System.out.println(maxDist);
    }

    static void dfs(int node, int dist) {
        visited[node] = true;
        if (dist > maxDist) {
            maxDist = dist;
            farthestNode = node;
        }

        for (Edge edge : adjList[node]) {
            if (!visited[edge.to]) {
                dfs(edge.to, dist + edge.cost);
            }
        }
    }
}