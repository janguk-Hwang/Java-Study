import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] parent, enemy;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        enemy = new int[n + 1];
        for(int i=1; i<=n; i++) parent[i] = i;
        boolean isEnemy = true;
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (find(a) == find(b)) {
                isEnemy = false;
                break;
            }
            if(enemy[a] == 0) enemy[a] = b;
            if(enemy[a] != 0 && enemy[a] != b) union(enemy[a], b);
            if(enemy[b] == 0) enemy[b] = a;
            if(enemy[b] != 0 && enemy[b] != a) union(enemy[b], a);
        }

        System.out.println(isEnemy ? 1 : 0);
    }

    public static int find(int a) {
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b) {
        a = find(a); b = find(b);
        parent[b] = a;
    }
}