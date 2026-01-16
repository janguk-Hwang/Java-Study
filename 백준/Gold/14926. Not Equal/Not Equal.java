import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static boolean[][] visited;
    static Stack<Integer> stack = new Stack<>();
    static List<Integer> path = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine().trim());
        visited = new boolean[n + 1][n + 1];
        stack.push(1);
        while(!stack.isEmpty()){
            int cur = stack.peek();
            boolean flag = false;
            for(int i=1; i<=n; i++){
                if(i == cur) continue;
                if(!visited[cur][i]){
                    visited[cur][i] = true;
                    visited[i][cur] = true;
                    stack.push(i);
                    flag = true;
                    break;
                }
            }
            if(!flag) path.add(stack.pop());
        }
        Collections.reverse(path);
        for(int i : path) sb.append('a').append(i).append(" ");
        System.out.print(sb);
    }
}