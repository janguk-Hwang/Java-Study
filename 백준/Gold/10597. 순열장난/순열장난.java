import java.io.*;
import java.util.*;

// 1부터 n까지의 수, 최대 50개의 수 => 1 ~ 50
public class Main {
    static String s;
    static boolean[] visited = new boolean[51];
    static boolean flag = false;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        s = br.readLine();
        backtracking(0, new ArrayList<>(), 0);
    }

    static void backtracking(int depth, List<Integer> path, int max) {
        if(flag) return;
        // 종료 조건
        if(depth == s.length()){
            for(int i=1; i<=max; i++) if(!visited[i]) return;
            for(int i=0; i<path.size(); i++){
                if(i > 0) sb.append(" ");
                sb.append(path.get(i));
            }
            System.out.print(sb);
            flag = true;
            System.exit(0);
        }

        // 1자리
        int num = s.charAt(depth) - '0';
        if(num >= 1 && num <= 9 && !visited[num]){
            visited[num] = true;
            path.add(num);
            backtracking(depth + 1, path, Math.max(max, num));
            path.remove(path.size() - 1);
            visited[num] = false;
        }

        // 2자리
        if(depth + 1 < s.length()){
            // num에 뒤에 숫자 하나를 추가
            num = Integer.parseInt(s.substring(depth, depth + 2));
            if(num >= 10 && num <= 50 && !visited[num]){
                visited[num] = true;
                path.add(num);
                backtracking(depth + 2, path, Math.max(max, num));
                path.remove(path.size() - 1);
                visited[num] = false;
            }
        }
    }
}