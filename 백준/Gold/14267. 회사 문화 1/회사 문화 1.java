import java.io.*;
import java.util.*;

// n, m이 최대 10만이므로 단순하게 구현하면 시간 초과
public class Main {
    static int n, m;
    static List<Integer>[] child;
    static int[] praise;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        child = new ArrayList[n + 1];
        for(int i=0; i<=n; i++) child[i] = new ArrayList<>();
        praise = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            int temp = Integer.parseInt(st.nextToken());
            if(temp == -1) continue;
            child[temp].add(i);
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            praise[a] += b;
        }
        // 사장(1)이 루트 노드
        func(1);
        for(int i=1; i<=n; i++) sb.append(praise[i]).append(" ");
        System.out.print(sb);
    }

    public static void func(int node){
        // node의 부하 직원들을 순회하면서 부하 직원들의 칭찬 수치를 상사가 받은 만큼 물려준다.
        for(Integer i : child[node]){
            praise[i] += praise[node];
            func(i);
        }
    }
}