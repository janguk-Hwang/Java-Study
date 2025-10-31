import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static String a, b, c;
    static boolean[][] visited;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            a = st.nextToken(); b = st.nextToken(); c = st.nextToken();
            visited = new boolean[a.length() + 1][b.length() + 1];
            sb.append("Data set ").append(i).append(": ").append(dfs(0, 0) ? "yes" : "no").append("\n");
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }

    static boolean dfs(int i, int j){
        if((i + j) == c.length()) return true;
        if(visited[i][j]) return false;
        visited[i][j] = true;
        boolean isPossible = false;
        if(i < a.length() && a.charAt(i) == c.charAt(i + j)){
            isPossible |= dfs(i+1, j);
        }
        if(j < b.length() && b.charAt(j) == c.charAt(i + j)){
            isPossible |= dfs(i, j+1);
        }
        return isPossible;
    }
}