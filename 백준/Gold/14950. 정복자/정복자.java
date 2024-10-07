import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int cost;
        int city;

        public Edge(int cost, int city) {
            this.cost = cost;
            this.city = city;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int t = Integer.parseInt(input[2]);

        List<int[]> roads = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            String[] in = br.readLine().split(" ");
            int A = Integer.parseInt(in[0]);
            int B = Integer.parseInt(in[1]);
            int C = Integer.parseInt(in[2]);
            roads.add(new int[]{A, B, C});
        }

        System.out.println(Prim(N, M, t, roads));
    }

    public static int Prim(int N, int M, int t, List<int[]> roads) {
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        //도로 입력
        for (int[] road : roads) {
            int A = road[0], B = road[1], C = road[2];
            graph.get(A).add(new Edge(C, B));
            graph.get(B).add(new Edge(C, A));
        }

        //edge.cost 기준
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.cost));
        boolean[] visited = new boolean[N + 1];

        pq.offer(new Edge(0, 1));  //초기 비용 0, 도시 1
        int totalCost = 0;
        int conqueredCities = 0;

        //우선순위 큐가 비고 정복한 도시가 N보다 작을 때까지
        while (!pq.isEmpty() && conqueredCities < N) {
            Edge current = pq.poll();
            int cost = current.cost;
            int city = current.city;

            //이미 방문한 도시면 패스
            if (visited[city]) {
                continue;
            }

            //도시 정복-방문 처리, 비용 계산
            visited[city] = true;
            //첫 번째 도시일 경우 추가 비용 없이 도로 비용만 더함
            if (conqueredCities == 0) {
                totalCost += cost;
            }
            else {
                totalCost += cost + (t * (conqueredCities - 1));  // 정복된 도시가 n개일 때 추가 비용은 (n-1) * t
            }
            conqueredCities++;

            //인접한 도시들을 큐에 추가
            for (Edge edge : graph.get(city)) {
                if (!visited[edge.city]) {
                    pq.offer(new Edge(edge.cost, edge.city));
                }
            }
        }

        return totalCost;
    }
}
