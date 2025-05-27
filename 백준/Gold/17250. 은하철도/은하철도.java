import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] parent;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        arr = new int[n+1];
        for(int i=1; i<=n; i++){
            parent[i] = i;
            arr[i] = Integer.parseInt(br.readLine());
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int root = union(a, b);
            sb.append(arr[root]).append("\n");
        }
        System.out.print(sb);
    }

    public static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    public static int union(int a, int b){
        a = find(a); b = find(b);
        if(a == b) return a;
        parent[b] = a;
        arr[a] += arr[b];
        return a;
    }
}