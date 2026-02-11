import java.io.*;
import java.util.*;

public class Main {
    static int n, m, q;
    static int[] parent;
    static int[] clean, dirty;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        clean = new int[n + 1];
        dirty = new int[n + 1];
        for(int i=1; i<=n; i++) parent[i] = i;
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            int temp = Integer.parseInt(st.nextToken());
            if(temp == 1) clean[i] = 1;
            else dirty[i] = 1;
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for(int i=0; i<q; i++){
            int k = Integer.parseInt(br.readLine());
            int root = find(k);
            sb.append(clean[root] > dirty[root] ? 1 : 0).append('\n');
        }
        sb.setLength(sb.length() - 1);
        System.out.print(sb);
    }

    static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        a = find(a); b = find(b);
        if (a == b) return;
        parent[b] = a;
        clean[a] += clean[b];
        dirty[a] += dirty[b];
    }
}