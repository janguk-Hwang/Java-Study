import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] parent, arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        for(int i=1; i<=n; i++) parent[i] = i;
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }
        int total = 0;
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        for(int i=1; i<n; i++){
            int b = Integer.parseInt(st.nextToken());
            if(find(a) != find(b)){
                total++;
            }
            a = b;
        }
        System.out.print(total);
    }

    public static void union(int a, int b){
        a = find(a); b = find(b);
        parent[b] = a;
    }
    public static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }
}