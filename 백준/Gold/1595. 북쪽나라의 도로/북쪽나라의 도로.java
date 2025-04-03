import java.util.*;
import java.io.*;

public class Main {
    static class Edge {
        int dest, cost;

        public Edge(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    static int maxDist, farthestNode;
    static ArrayList<Edge>[] adjList;   // 연결되어 있는 도시를 담는 리스트
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        adjList = new ArrayList[10001];
        for (int i = 0; i < 10001; i++) {
            adjList[i] = new ArrayList<>();
        }

        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            StringTokenizer st = new StringTokenizer(line);
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // 양방향 추가
            adjList[s].add(new Edge(e, cost));
            adjList[e].add(new Edge(s, cost));
        }

        visited = new boolean[10001];
        maxDist = 0;
        dfs(1, 0);
        
        visited = new boolean[10001];   // visited, maxDist 초기화하고 dfs 한번 더 실행
        maxDist = 0;
        dfs(farthestNode, 0);

        System.out.println(maxDist);
    }

    static void dfs(int node, int dist) {   // 현재 방문 중인 노드의 번호, 시작 노드부터 현재 노드까지의 거리
        visited[node] = true;
        if (dist > maxDist) {
            maxDist = dist;
            farthestNode = node;
        }

        for (Edge edge : adjList[node]) {
            if (!visited[edge.dest]) {
                dfs(edge.dest, dist + edge.cost);
            }
        }
    }
}