import java.util.*;
import java.io.*;

// 풀이 과정
// 1. 공의 시작점은 빈 공간에서 선택하며, 모든 가능한 시작점을 탐색한다.
// 2. 사방탐색을 수행하며 이동할 수 있는 최대한의 거리를 이동한다.
// 3. 이동 중 보드를 벗어나거나 장애물(*)을 만나거나, 이미 방문한 곳을 만나면 멈춘다.
// 4. 모든 빈 공간을 방문하는 최소 이동 횟수를 찾는다.
// 5. 백트래킹을 활용하여 방문 여부를 원래 상태로 되돌리면서 탐색을 진행한다.
public class Main {
    static int n, m;
    static char[][] board; // 보드 정보
    static boolean[][] visited; // 방문 여부 체크
    static int[] dx = {1, 0, -1, 0}; // (하, 우, 상, 좌)
    static int[] dy = {0, 1, 0, -1};
    static int minMove; // 최소 이동 횟수
    static int emptyCount; // 빈 공간의 개수
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;
        int caseNum = 1; // 출력값에 사용할 테스트 케이스 번호

        // line에 담지 않고 br.readLine()의 결과로 EOF를 처리하면 입력을 잃을 수 있기 때문에 변수에 담아서 사용
        while ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);
            n = Integer.parseInt(st.nextToken());   // 세로 크기
            m = Integer.parseInt(st.nextToken());   // 가로 크기

            board = new char[n][m]; // 맵
            visited = new boolean[n][m]; // 방문 배열
            emptyCount = 0; // 빈 공간 개수
            List<int[]> start_xy = new ArrayList<>(); // 시작 가능한 공의 위치 리스트

            // 입력 받기
            for (int i = 0; i < n; i++) {
                String str = br.readLine();
                for (int j = 0; j < m; j++) {
                    board[i][j] = str.charAt(j);
                    if (board[i][j] == '.') {   // 빈 공간일 경우
                        start_xy.add(new int[]{i, j});  // 시작 가능한 공의 위치 리스트에 add
                        emptyCount++; // 빈 공간 개수 증가
                    }
                }
            }

            minMove = Integer.MAX_VALUE; // 최소 이동 횟수 초기화

            // 모든 가능한 시작점에서 탐색 수행
            for (int[] start : start_xy) {
                // 방문 배열 초기화
                for (int i = 0; i < n; i++) {
                    Arrays.fill(visited[i], false);
                }

                // 시작점 방문 표시 후 DFS 탐색
                visited[start[0]][start[1]] = true;
                dfs(start[0], start[1], 0, 1);
            }

            // 모든 빈 공간을 방문할 수 없는 경우 -1 출력
            System.out.println("Case " + caseNum + ": " + (minMove == Integer.MAX_VALUE ? -1 : minMove));
            caseNum++; // 테스트 케이스 번호 증가
        }
    }

    // DFS 탐색 (현재 위치 x, y, 이동 횟수 moves, 방문한 빈 공간 개수 visitedCount)
    static void dfs(int x, int y, int moves, int visitedCount) {
        // 모든 빈 공간을 방문한 경우, 최소 이동 횟수 갱신
        if (visitedCount == emptyCount) {
            minMove = Math.min(minMove, moves);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nx = x, ny = y; // 이동한 후의 위치
            boolean moved = false; // 이동 여부 체크

            // 이동 가능한 최대 거리까지 이동
            while (true) {
                int tempx = nx + dx[d]; // 임시 위치
                int tempy = ny + dy[d];

                // 이동 불가능한 경우 (경계 이탈, 장애물 직면, 이미 방문한 곳)
                if (tempx < 0 || tempx >= n || tempy < 0 || tempy >= m || board[tempx][tempy] == '*' || visited[tempx][tempy]) {
                    break;
                }

                nx = tempx;
                ny = tempy;
                visited[tempx][tempy] = true;
                moved = true;
            }

            // 이동한 경우 DFS 탐색 수행 후 방문 상태 복구
            if (moved) {
                dfs(nx, ny, moves + 1, visitedCount + countVisited(x, y, nx, ny, d));
                backtraking(x, y, nx, ny, d);
            }
        }
    }

    // 특정 방향으로 방문한 칸 개수 계산
    static int countVisited(int x, int y, int nx, int ny, int d) {
        int count = 0;
        while (x != nx || y != ny) {
            x += dx[d];
            y += dy[d];
            count++;
        }
        return count;
    }

    // 특정 방향으로 방문했던 칸을 되돌림 (백트래킹)
    static void backtraking(int x, int y, int nx, int ny, int d) {
        while (x != nx || y != ny) {
            x += dx[d];
            y += dy[d];
            visited[x][y] = false;
        }
    }
}
