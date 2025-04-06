import java.io.*;
import java.util.*;

public class Main {
    static int[] fee;
    static int[] parent;
    static int sum;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        sum = 0;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        fee = new int[n];
        parent = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            fee[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            union(a, b);
        }

        // 대표 노드만 sum에 더하기 (중복 방지)
//        Set<Integer> roots = new HashSet<>();
//        for (int i = 0; i < n; i++) {
//            roots.add(find(i));
//        }
//
//        for (int r : roots) {
//            sum += fee[r];
//        }
        for(int i=0; i<n; i++){
            if(parent[find(i)] == i){
                sum += fee[i];
            }
        }

        if (sum <= k) {
            System.out.println(sum);
        } else {
            System.out.println("Oh no");
        }
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;

        if (fee[a] < fee[b]) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    public static int find(int a) {
        if (parent[a] != a) {
            parent[a] = find(parent[a]);
        }
        return parent[a];
    }
}
