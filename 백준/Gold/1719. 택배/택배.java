import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 100000000; // 무한대 값 설정
    static int[][] route; // 경로표 저장
    static int[][] dist; // 최단 거리 저장
    static List<Node>[] graph; // 그래프 인접 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int n = Integer.parseInt(st.nextToken()); // 집하장의 개수
        int m = Integer.parseInt(st.nextToken()); // 경로의 개수
        
        // 그래프 및 테이블 초기화
        graph = new ArrayList[n + 1];
        dist = new int[n + 1][n + 1];
        route = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            Arrays.fill(dist[i], INF);
            Arrays.fill(route[i], -1);
            dist[i][i] = 0;
        }

        // 입력 받기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, cost));
            graph[v].add(new Node(u, cost)); // 양방향 그래프
        }

        // 각 노드에서 다익스트라 실행
        for (int i = 1; i <= n; i++) {
            dijkstra(i, n);
        }

        // 출력
        printTable(n);
    }

    static void dijkstra(int start, int n) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        
        int[] distance = new int[n + 1];
        Arrays.fill(distance, INF);
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int curNode = now.index;
            int curCost = now.cost;

            if (curCost > distance[curNode]) continue;

            for (Node next : graph[curNode]) {
                int newDist = distance[curNode] + next.cost;

                if (newDist < distance[next.index]) {
                    distance[next.index] = newDist;
                    pq.offer(new Node(next.index, newDist));
                    route[start][next.index] = (curNode == start) ? next.index : route[start][curNode];
                }
            }
        }

        // 거리 정보 저장
        for (int i = 1; i <= n; i++) {
            dist[start][i] = distance[i];
        }
    }

    static void printTable(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) sb.append("- ");
                else sb.append(route[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static class Node implements Comparable<Node> {
        int index, cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return Integer.compare(this.cost, n.cost);
        }
    }
}
