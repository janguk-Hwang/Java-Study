import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] parent;
    static char[][] matrix;
    static Set<Integer> set = new HashSet<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n * m];
        matrix = new char[n][m];
        for(int i=0; i<n*m; i++) parent[i] = i;
        for(int i=0; i<n; i++){
            String line = br.readLine();
            matrix[i] = line.toCharArray();
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                int now = i * m + j;
                int nr = i; int nc = j;
                switch(matrix[i][j]){
                    case 'U' : nr -= 1; break;
                    case 'D' : nr += 1; break;
                    case 'L' : nc -= 1; break;
                    case 'R' : nc += 1; break;
                    default : break;
                }
                int next = nr * m + nc;
                union(now, next);
            }
        }
        for(int i=0; i<n*m; i++) set.add(find(i));
        System.out.print(set.size());
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