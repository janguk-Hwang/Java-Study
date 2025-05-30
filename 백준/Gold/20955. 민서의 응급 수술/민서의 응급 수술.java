import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] parent;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        for(int i=0; i<=n; i++) parent[i] = i;
        int cnt = 0;    // 끊어야 하는 뉴런 수
        int tempM = m;
        while(m-- > 0){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if(find(u) == find(v)){
                cnt++;
                continue;
            }
            union(u, v);
        }
        int haveToConnect = n - 1 - (tempM - cnt);
        System.out.print(cnt + haveToConnect);
    }

    public static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b){
        a = find(a); b = find(b);
        parent[b] = a;
    }
}