import java.io.*;
import java.util.*;

// 특정 도시를 두 번 이상 지나가지 않고서 임의의 두 도시간을 이동하는 경로가 유일하도록 도로가 설계되어 있기 때문에
// 사이클이 없다는 것을 의미하고 사이클이 없다는 것은 트리 구조라는 것을 의미한다.
class Main {
    static class Node {
        int to, weight;
        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static List<Node>[] tree;   // 트리를 저장하는 인접 리스트
    static boolean[] visited;
    static int maxDistance = 0;     // 최대 거리 저장 변수
    static int farthestNode = 0;    // 가장 먼 노드를 저장할 변수
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        tree = new ArrayList[10001];    // 바로 최대인 10001 크기만큼 만드는 것보다 도시의 개수만큼 만드는 것이 좋을 것 같음
        for (int i = 0; i < 10001; i++) {
            tree[i] = new ArrayList<>();
        }

        HashSet<Integer> nodes = new HashSet<>(); // 입력된 노드 저장
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