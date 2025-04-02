import java.io.*;
import java.util.*;

class Main {
    static class Node {
        int to, weight;
        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static List<Node>[] tree;
    static boolean[] visited;
    static int maxDistance = 0;
    static int farthestNode = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tree = new ArrayList[10001];
        for (int i = 0; i < 10001; i++) {
            tree[i] = new ArrayList<>();
        }

        Set<Integer> nodes = new HashSet<>(); // 입력된 노드 저장
        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) { // 입력이 없으면 종료
            String[] str = line.split(" ");
            int u = Integer.parseInt(str[0]);
            int v = Integer.parseInt(str[1]);
            int w = Integer.parseInt(str[2]);
            tree[u].add(new Node(v, w));
            tree[v].add(new Node(u, w));
            nodes.add(u);
            nodes.add(v);
        }

        // 입력된 노드가 없는 경우 프로그램 종료
        Iterator<Integer> it = nodes.iterator();
        if (!it.hasNext()) {
            System.out.println(0);
            return;
        }

        // 입력된 노드 중 하나를 시작점으로 선택
        int startNode = it.next();

        // 첫 번째 DFS: 임의의 노드에서 가장 먼 노드 찾기
        visited = new boolean[10001];
        dfs(startNode, 0);

        // 두 번째 DFS: 찾은 노드에서 가장 먼 거리 찾기
        visited = new boolean[10001];
        maxDistance = 0;
        dfs(farthestNode, 0);

        System.out.println(maxDistance);
    }

    static void dfs(int node, int distance) {
        visited[node] = true;
        if (distance > maxDistance) {
            maxDistance = distance;
            farthestNode = node;
        }

        for (Node next : tree[node]) {
            if (!visited[next.to]) {
                dfs(next.to, distance + next.weight);
            }
        }
    }
}
