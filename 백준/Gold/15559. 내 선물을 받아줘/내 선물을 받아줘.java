import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static char[][] matrix;
    static int[] parent;
    static Set<Integer> set = new HashSet<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new char[n][m];
        parent = new int[n * m + 1];
        for(int i=0; i<=n*m; i++) parent[i] = i;
        for(int i=0; i<n; i++){
            String[] line = br.readLine().split("");
            for(int j=0; j<m; j++) matrix[i][j] = line[j].charAt(0);
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                // 지도에 쓰여 있는대로 이동했을 때, 지도를 벗어나는 경우는 없다.
                // 범위를 벗어나는 경우를 고려하지 않아도 된다.
                if(matrix[i][j] == 'N'){
                    union(parent[i * m + j], parent[(i-1) * m + j]);
                    continue;
                }
                if(matrix[i][j] == 'E'){
                    union(parent[i * m + j], parent[i * m + j + 1]);
                    continue;
                }
                if(matrix[i][j] == 'S'){
                    union(parent[i * m + j], parent[(i + 1) * m + j]);
                    continue;
                }
                if(matrix[i][j] == 'W') union(parent[i * m + j], parent[i * m + j - 1]);
            }
        }
        for(int i=0; i<n; i++) for(int j=0; j<m; j++) set.add(find(i * m + j));
        System.out.print(set.size());
    }

    static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b){
        a = find(a); b = find(b);
        parent[a] = b;
    }
}