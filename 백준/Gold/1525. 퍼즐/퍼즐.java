import java.io.*;
import java.util.*;

// 칸을 움직여서 완성된 그림으로 만드는 퍼즐
// '최소의 이동으로'
public class Main {
    static Queue<Shape> q = new LinkedList<>();
    static Set<String> visited = new HashSet<>();
    static final String goal = "123456780";
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        for(int i=0; i<3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) sb.append(st.nextToken());
        }
        System.out.print(bfs(String.valueOf(sb)));
    }

    static int bfs(String s){
        q.add(new Shape(s, 0));
        visited.add(s);
        while(!q.isEmpty()){
            Shape cur = q.poll();
            if(cur.boardState.equals(goal)) return cur.cnt;
            int zeroIdx = cur.boardState.indexOf('0');
            int r = zeroIdx / 3;
            int c = zeroIdx % 3;
            for(int d=0; d<4; d++){
                // 0의 다음 좌표
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(nr < 0 || nr >= 3 || nc < 0 || nc >= 3) continue;
                // swap
                char[] arr = cur.boardState.toCharArray();
                char temp = arr[zeroIdx];
                arr[zeroIdx] = arr[nr * 3 + nc];
                arr[nr * 3 + nc] = temp;
                String newShape = new String(arr);
                if(visited.contains(newShape)) continue;
                visited.add(newShape);
                q.add(new Shape(newShape, cur.cnt + 1));
            }
        }
        return -1;
    }

    static class Shape{
        String boardState;
        int cnt;
        Shape(String board, int cnt){
            this.boardState = board;
            this.cnt = cnt;
        }
    }
}