import java.io.*;
import java.util.*;

// 깊이가 증가하면서 함수 재귀 호출, 이동할 수 있는 곳이 없으면 함수 종료되면서 회수
// 각 단계에서 모든 방향으로 끝까지 들어가면서 최대 이동칸 수 갱신
public class Main {
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[] visited;   // 알파벳 출현 여부 저장
    static int r, c, max;
    static char[][] matrix;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        matrix = new char[r][c];
        visited = new boolean[26];
        for(int i=0; i<r; i++) matrix[i] = br.readLine().toCharArray();
        visited[matrix[0][0] - 'A'] = true;
        func(0, 0, 1);
        System.out.print(max);
    }

    public static void func(int y, int x, int depth){
        max = Math.max(max, depth);
        for(int d=0; d<4; d++){
            int nr = y + dr[d];
            int nc = x + dc[d];
            if(nr < 0 || nr >= r || nc < 0 || nc >= c) continue;
            int nextAlpa = matrix[nr][nc] - 'A';
            if(!visited[nextAlpa]){
                visited[nextAlpa] = true;
                func(nr, nc, depth + 1);    // 깊이를 증가시킨 채 새 좌표에서 func() 호출
                visited[nextAlpa] = false;      // 함수 회수되면서 되돌려야 할 것
            }
        }
    }
}