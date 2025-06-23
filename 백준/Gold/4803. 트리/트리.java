import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] parent, size, edgeCnt;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int testCaseNum = 1;
        while(true){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if(n == 0 && m == 0) break;
            parent = new int[n+1];
            size = new int[n+1];
            edgeCnt = new int[n+1];
            for(int i=1; i<=n; i++){
                parent[i] = i;
                size[i] = 1;
                edgeCnt[i] = 0;
            }
            for(int i=0; i<m; i++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                union(s, e);
            }
            Set<Integer> set = new HashSet<>();
            // 집합의 크기는 트리의 수
            for(int i=1; i<=n; i++) set.add(find(i));
            int cnt = 0;
            for(Integer root : set){
                if(edgeCnt[root] == size[root] - 1) cnt++;
            }
            sb.append("Case ").append(testCaseNum++).append(": ");
            if(cnt == 0) sb.append("No trees.").append("\n");
            if(cnt == 1) sb.append("There is one tree.").append("\n");
            if(cnt > 1) sb.append("A forest of ").append(cnt).append(" trees.").append("\n");
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }

    public static void union(int a, int b){
        a = find(a); b = find(b);
        if(a != b){
            parent[b] = a;
            size[a] += size[b];
            edgeCnt[a] += edgeCnt[b];
        }
        edgeCnt[a]++;
    }

    public static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }
}