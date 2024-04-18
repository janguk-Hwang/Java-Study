import java.util.ArrayDeque;

class Solution {
    static ArrayDeque<int[]> Q;
    static int N, M; // 행, 열 크기 1, 1인 경우는 주어지지 않음.
    static boolean[][] visited; // 방문 체크 2차원 배열
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1 ,1};
    static int[][] board;
    static int dist = 0;
    static boolean flag = false;

    static void bfs() {
        Q = new ArrayDeque<>();
        Q.addLast(new int[] {0, 0}); // 맨 처음 점은 0, 0 입니다. (문제에선 1, 1)
        visited[0][0] = true;

        while (!Q.isEmpty()) {
            int size = Q.size();
            for (int i = 0; i < size; i++) { // 사이즈만큼 모조리 뽑으면서
                int[] current = Q.removeFirst();
                int cur_r = current[0];
                int cur_c = current[1];

                if (cur_r == N-1 && cur_c == M-1) { // 해당 위치를 찾은 경우는?
                    flag = true; // 찾았다고 체크하고
                    Q.clear(); // 더블 브레이크 (위의 while문도 터짐)
                    break; // 브레이크 대신 리턴해버리면 clear() 안써도 됩니다.
                }

                for (int j = 0; j < 4; j++) {
                    int nr = cur_r + dr[j];
                    int nc = cur_c + dc[j];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                        continue; // 맵 밖이면 안되고
                    }

                    if (board[nr][nc] == 0 || visited[nr][nc]) {
                        continue; // 벽이거나 이미 방문했으면 안되고
                    }
                    visited[nr][nc] = true;
                    Q.addLast(new int[] {nr, nc});
                }
            }
            dist++;
        }
    }

    static int solution(int[][] maps) {
        board = maps;
        N = maps.length; // 행 갯수
        M = maps[0].length; // 열 갯수
        visited = new boolean[N][M]; // 모두 false로 초기화
        bfs(); // bfs를 돌립니다!
        int answer = flag ? dist : -1;

        return answer;
    }
}