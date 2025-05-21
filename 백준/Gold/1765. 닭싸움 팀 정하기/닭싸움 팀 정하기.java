import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] parent;
    static ArrayList<Integer>[] enemy;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        parent = new int[n+1];
        enemy = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            parent[i] = i;
            enemy[i] = new ArrayList<>();
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            String s = st.nextToken();
            int friend1 = Integer.parseInt(st.nextToken());
            int friend2 = Integer.parseInt(st.nextToken());
            if(s.equals("F")) union(friend1, friend2);
            else{
                for(int temp : enemy[friend1]){
                    union(temp, friend2);
                }
                for(int temp : enemy[friend2]){
                    union(temp, friend1);
                }
                enemy[friend1].add(friend2);
                enemy[friend2].add(friend1);
            }
        }
        Set<Integer> totalTeam = new HashSet<>();
        for(int i=1; i<=n; i++) totalTeam.add(find(i));
        System.out.print(totalTeam.size());
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