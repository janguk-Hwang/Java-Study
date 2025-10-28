import java.io.*;
import java.util.*;

public class Main {
    static int t, n, m, k;
    static int[] parent;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        for(int j=1; j<=t; j++){
            n = Integer.parseInt(br.readLine());
            k = Integer.parseInt(br.readLine());
            parent = new int[n];
            for(int i=0; i<n; i++) parent[i] = i;
            for(int i=0; i<k; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }
            sb.append("Scenario ").append(j).append(":").append("\n");
            m = Integer.parseInt(br.readLine());
            for(int i=0; i<m; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if(find(a) == find(b)) sb.append(1).append("\n");
                else sb.append(0).append("\n");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void union(int a, int b){
        a = find(a); b = find(b);
        parent[b] = a;
    }

    static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }
}