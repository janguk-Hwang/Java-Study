import java.io.*;
import java.util.*;

public class Main {
    static Set<Integer> set = new HashSet<>();
    static List<Integer> list = new ArrayList<>();
    static int n, m;
    static int[] parent, groupSize;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        for(int i=1; i<=n; i++) parent[i] = i;
        groupSize = new int[n + 1];
        Arrays.fill(groupSize, 1);
        m = Integer.parseInt(st.nextToken());
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }
        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int cRoot = find(c);
        int hRoot = find(h);
        for(int i=1; i<=n; i++){
            int root = find(i);
            if(root != cRoot && root != hRoot && !set.contains(root)){
                set.add(root);
                list.add(root);
            }
        }
        list.sort((a,b) -> groupSize[b] - groupSize[a]);
        for(int i=0; i<Math.min(k, list.size()); i++) union(c, list.get(i));
        System.out.print(groupSize[find(c)]);
    }

    static void union(int a, int b){
        a = find(a); b = find(b);
        if(a == b) return;
        parent[b] = a;
        groupSize[a] += groupSize[b];
    }

    static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }
}
