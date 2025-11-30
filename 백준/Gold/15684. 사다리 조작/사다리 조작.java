import java.io.*;
import java.util.*;

// 규칙을 찾지 못했으므로 추가할 수 있는 가로선의 가능한 모든 조합에 대해 항등이 되는지 검사
// 백트래킹, 조합론
// 만약, 정답이 3보다 큰 값이면 -1을 출력한다. 또, 불가능한 경우에도 -1을 출력한다.
public class Main {
    static int n, m, h;
    static boolean[][] visited;
    static boolean flag;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        visited = new boolean[h + 1][n + 1];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            visited[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
        }
        flag = false;
        for(int i=0; i<=3; i++){
            backtracking(1, 1, 0, i);
            // i개 가로선을 추가해서 항등이 되도록 사다리를 조작했으면 더 이상 진행 x
            if(flag){
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }

    // 행, 열, 현재까지 추가한 가로선 수, 총 추가해볼 가로선 수
    static void backtracking(int row, int col, int depth, int target) {
        // 종료 조건
        if(flag) return;
        if(depth == target){
            if(isIdentity()) flag = true;
            return;
        }
        // 가능한 선택
        for(int r=row; r<=h; r++){
            for(int c=1; c<n; c++){
                if(r == row && c < col) continue;
                // 이미 가로선이 있으면 x
                if(visited[r][c]) continue;
                // 왼쪽 세로선에 인접한 가로선 존재 여부 확인
                if(c - 1 >= 1 && visited[r][c - 1]) continue;
                // 오른쪽 세로선 ``
                if(c + 1 <= n - 1 && visited[r][c + 1]) continue;
                visited[r][c] = true;
                backtracking(r, c, depth + 1, target);
                visited[r][c] = false;
                if(flag) return;
            }
        }
    }

    // 항등인지 검사
    static boolean isIdentity() {
        for(int i=1; i<=n; i++){
            int pos = i;
            for(int r=1; r<=h; r++){
                if(pos <= n-1 && visited[r][pos]){
                    pos++;
                    continue;
                }
                if(pos-1 >= 1 && visited[r][pos - 1]) pos--;
            }
            if(pos != i) return false;
        }
        return true;
    }
}