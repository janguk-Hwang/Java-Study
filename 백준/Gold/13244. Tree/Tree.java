import java.io.*;
import java.util.*;

// 모든 노드는 다른 노드로 도달할 수 있다.
// 한 간선을 끊으면 그래프는 더 이상 연결되지 않는다. 즉, 서로 도달할 수 없는 노드 쌍이 생긴다.
// 기존의 두 노드를 연결하는 한 간선이 추가되면 사이클이 발생한다. A에서 B로 가는 경로가 1개보다 많으면 사이클이 발생한다.
// 할 일은 주어진 그래프가 트리인지 아닌지 판단하는 것이다.
// 즉, 사이클이 발생하는지와 간선의 수가 노드의 수 - 1과 동일한지 확인해야 한다.
public class Main {
    static int t, n, m;
    static int[] parent;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            n = Integer.parseInt(br.readLine());    // 노드는 1번부터 n번까지
            m = Integer.parseInt(br.readLine());    // 간선의 수
            if(m != n - 1){
                for(int i=0; i<m; i++) br.readLine();
                sb.append("graph\n");
                continue;
            }
            parent = new int[n + 1];
            for(int i=1; i<=n; i++) parent[i] = i;
            boolean hasCycle = false;
            for(int i=0; i<m; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if(find(a) == find(b)) hasCycle = true;
                union(a, b);
            }
            if(hasCycle){
                sb.append("graph").append("\n");
                continue;
            }
            if(!hasCycle) sb.append("tree").append("\n");
        }
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
}