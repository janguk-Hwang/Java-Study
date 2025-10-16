import java.io.*;
import java.util.*;

// 연결되지 않은 무게제한이 큰 도로부터 연결한다.
// 출발점과 도착점이 연결될 때 해당 간선의 무게 제한이 출발점과 도착점을 연결하는 도로 중 가장 무게제한이 작은 간선이다.
public class Main {
    static List<int[]> list = new ArrayList<>();
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int[] parent;
    static int[][] arr;
    static int n, totCost, bonusCost;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        parent = new int[n + 1];
        for(int i=0; i<=n; i++) parent[i] = i;
        arr = new int[n + 1][n + 1];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(j > i){
                    pq.add(new Node(i, j, arr[i][j]));
                    if(arr[i][j] < 0){
                        totCost += -arr[i][j];
                        union(i, j);
                    }
                }
            }
        }
        // 이미 연결되어 있는 간선은 union()
        int edgeCnt = 0;
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(find(cur.from) != find(cur.to)){
                union(cur.from, cur.to);
                bonusCost += cur.cost;
                edgeCnt++;
                list.add(new int[]{cur.from, cur.to});
            }
        }
        sb.append((totCost+bonusCost)).append(" ").append(edgeCnt).append("\n");
        for(int[] temp : list) sb.append(temp[0]).append(" ").append(temp[1]).append("\n");
        sb.setLength(sb.length() - 1);
        System.out.print(sb);
    }

    static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b){
        a = find(a); b = find(b);
        parent[b] = a;
    }

    static class Node implements Comparable<Node>{
        int from; int to; int cost;
        Node(int from, int to, int cost){
            this.from = from; this.to = to; this.cost = cost;
        }
        @Override
        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }
}