import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] parent;
    static List<Integer>[] adj;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        parent = new int[n+1];
        for(int i=1; i<=n; i++) parent[i] = i;
        adj = new ArrayList[n + 1];
        for(int i=1; i<=n; i++) adj[i] = new ArrayList<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a,b);
            adj[a].add(b);
            adj[b].add(a);
        }

        // 위원회 구성원 모으기
        Map<Integer, List<Integer>> member = new HashMap<>();
        for(int i=1; i<=n; i++){
            int node = find(i);
            member.putIfAbsent(node, new ArrayList<>());
            member.get(node).add(i);
        }

        // 각 위원회 대표 선정
        List<Integer> rep = new ArrayList<>();
        for(List<Integer> committee : member.values()) rep.add(bfs(committee));
        Collections.sort(rep);
        System.out.println(rep.size());
        for(int i : rep) System.out.println(i);
    }

    static int find(int x){
        if(parent[x]==x) return x;
        return parent[x]=find(parent[x]);
    }

    static void union(int a,int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA != rootB) parent[rootB] = rootA;
    }

    // 위원회 대표 찾기, 다 방문 가능한 노드들만 있기 때문에 visited 대신 dist[]의 초기값으로 방문 여부 확인
    static int bfs(List<Integer> committee){
        int min = Integer.MAX_VALUE;
        int rep = -1;
        for(int node : committee){
            Queue<Integer> q = new LinkedList<>();
            q.add(node);
            int[] dist = new int[n+1];
            Arrays.fill(dist,-1);
            dist[node] = 0;
            while(!q.isEmpty()){
                int cur = q.poll();
                for(int i : adj[cur]){
                    if(dist[i] == -1){
                        dist[i] = dist[cur] + 1;
                        q.add(i);
                    }
                }
            }
            int representer = 0;
            for(int i : committee) representer = Math.max(representer, dist[i]);
            if(representer < min){
                min = representer;
                rep = node;
            }
        }
        return rep;
    }
}