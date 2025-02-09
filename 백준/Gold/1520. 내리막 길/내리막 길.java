import java.util.*;
import java.io.*;

public class Main {
    static int m, n;
    static int[][] matrix;
    static int[][] dp;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    // DFS 방식으로 경로를 찾고 동적 계획법을 통해 이미 계산된 결과를 재사용
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        matrix = new int[m][n];
        dp = new int[m][n];
        
        // dp 배열을 -1로 초기화 (방문 여부 확인용)
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }

        // 높이 입력받기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dfs(0, 0));
    }

    public static int dfs(int r, int c) {
        // 목표 지점에 도달했으면 1 반환
        if (r == m - 1 && c == n - 1) {
            return 1;
        }

        // 이미 계산된 경로가 있으면 그 값을 반환
        // 일반 dp 문제처럼 메모라이제이션
        if (dp[r][c] != -1) {
            return dp[r][c];
        }

        dp[r][c] = 0; // 초기화 후 탐색 시작

        // 4방향으로 탐색
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            // 배열 범위 내에서, 현재 위치보다 높은 곳으로만 이동
            if (nr >= 0 && nr < m && nc >= 0 && nc < n && matrix[r][c] > matrix[nr][nc]) {
                dp[r][c] += dfs(nr, nc);
            }
        }

        return dp[r][c];
    }
}