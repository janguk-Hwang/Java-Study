import java.io.*;
import java.util.*;

// 1 ~ 9
// 백트래킹으로 중복없는 수들을 만들면서 가장 작은 수를 갱신
// 362,880가지
public class Main {
    static boolean[] visited;
    static boolean flag;
    static int n, rst;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String line;
        while((line = br.readLine()) != null && !line.isEmpty()){
            n = Integer.parseInt(line);
            rst = 0;
            flag = false;
            visited = new boolean[10];
            for(int i=String.valueOf(n).length(); i<=9 && !flag; i++){
                // i 길이의 수까지만 탐색
                // 만약 찾았으면 더 긴 길이의 수는 탐색 x
                backtracking(0, 0, i);
            }
            sb.append(rst).append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

    static void backtracking(int depth, int num, int len){
        if(flag) return;
        if(depth == len){
            if(num > n){
                rst = Math.max(rst, num);
                flag = true;
            }
            return;
        }

        for(int i=1; i<=9; i++){
            if(!visited[i]){
                visited[i] = true;
                backtracking(depth + 1, num * 10 + i, len);
                visited[i] = false;
            }
        }
    }
}