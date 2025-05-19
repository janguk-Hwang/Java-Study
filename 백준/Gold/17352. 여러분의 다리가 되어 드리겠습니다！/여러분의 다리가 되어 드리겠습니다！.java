import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] parent;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        parent = new int[n+1];
        for(int i=1; i<n; i++) parent[i] = i;
        for(int i=0; i<n-2; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }
        for(int i=1; i<n; i++){
            if(find(parent[i]) != find(parent[i+1])){
                System.out.print(i + " " + (i+1));
                return;
            }
        }
    }

    public static void union(int a, int b){
        a = find(a);
        b = find(b);
        parent[b] = a;
    }

    public static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
}