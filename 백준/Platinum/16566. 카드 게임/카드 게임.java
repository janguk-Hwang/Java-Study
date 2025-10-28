import java.io.*;
import java.util.*;

// 이분 탐색으로 철수가 낸 카드 번호보다 높은 카드를 찾는다.
// union-find로 parent를 더 높은 숫자로 연결해 놓아서
// 이후에 자신보다 크면서 사용되지 않는 가장 작은 숫자로 연결되도록
public class Main {
    static int n, m, k;
    static int[] parent;
    static int[] list;
    static int[] culsu;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        list = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) list[i] = Integer.parseInt(st.nextToken());
        culsu = new int[k];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++) culsu[i] = Integer.parseInt(st.nextToken());
        parent = new int[m + 1];
        for(int i = 1; i <= m; i++) parent[i] = i;
        Arrays.sort(list);
        for(int i : culsu){
            int idx = Arrays.binarySearch(list, i);
            if(idx >= 0) idx++;
            else idx = -(idx + 1);
            int next = find(idx);
            sb.append(list[next]).append("\n");
            union(next, next + 1);
        }
        System.out.print(sb);
    }

    static void union(int a, int b){
        a = find(a); b = find(b);
        parent[a] = b;
    }

    static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }
}