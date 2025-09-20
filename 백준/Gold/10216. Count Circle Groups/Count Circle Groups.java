import java.io.*;
import java.util.*;

public class Main {
    static int t, n;
    static int[][] arr;
    static int[] parent;
    static Set<Integer> set;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            n = Integer.parseInt(br.readLine());
            arr = new int[n][3];    // 0: x, 1: y, 2: R
            parent = new int[n];
            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<3; j++) arr[i][j] = Integer.parseInt(st.nextToken());
                parent[i] = i;
            }

            for(int i=0; i<n; i++){
                for(int j=i+1; j<n; j++){
                    if(isLinked(i, j)) union(i, j);
                }
            }
            
            set = new HashSet<>();
            // 대표 노드들을 집합에 넣어 그룹의 수 세기
            for(int i=0; i<n; i++) set.add(find(i));
            sb.append(set.size()).append("\n");
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }

    static boolean isLinked(int i, int j){
        int dx = arr[i][0] - arr[j][0];
        int dy = arr[i][1] - arr[j][1];
        double dist = Math.sqrt(dx * dx + dy * dy);
        return dist <= arr[i][2] + arr[j][2];
    }

    static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);
        parent[a] = b;
    }
}