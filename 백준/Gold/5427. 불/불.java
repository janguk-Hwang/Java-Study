import java.util.*;
import java.io.*;

public class Main {
    static int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            char[][] matrix = new char[h][w]; // 2차원 맵
            Queue<int[]> fireQueue = new LinkedList<>(); // 불의 확산을 담는 큐
            Queue<int[]> playerQueue = new LinkedList<>(); // 플레이어 이동을 담는 큐
            boolean[][] visited = new boolean[h][w];

            //'.': 빈 공간
            //'#': 벽
            //'@': 상근이의 시작 위치
            //'*': 불
            // 입력 받기
            for (int i = 0; i < h; i++) {
                String str = br.readLine();
                for (int j = 0; j < w; j++) {
                    matrix[i][j] = str.charAt(j);
                    if (matrix[i][j] == '@') { // 상근이의 현재 위치를 만나면
                        playerQueue.add(new int[]{i, j, 0}); // (x, y, 이동 시간)
                        visited[i][j] = true; // 방문 처리
                    }
                    else if (matrix[i][j] == '*') { // 불이 난 위치
                        fireQueue.add(new int[]{i, j}); // (x, y)
                    }
                }
            }

            // bfs를 수행하며 탈출 시간 계산
            int result = bfs(matrix, h, w, playerQueue, fireQueue, visited);
            System.out.println(result == -1 ? "IMPOSSIBLE" : result);
        }
    }

    public static int bfs(char[][] matrix, int h, int w, Queue<int[]> playerQueue, Queue<int[]> fireQueue, boolean[][] visited) {
        while (!playerQueue.isEmpty()) {
            // 불이 옮겨진 칸 또는 이제 불이 붙으려는 칸으로 이동할 수 없다.
            // 1. 불 먼저 확산
            int fireSize = fireQueue.size();
            for (int i = 0; i < fireSize; i++) {
                int[] fire = fireQueue.poll();
                int fx = fire[0], fy = fire[1];
                for (int[] dir : directions) {
                    int nfx = fx + dir[0], nfy = fy + dir[1];
                    // 불이 퍼질 수 있는 빈 공간이면 확산
                    if (nfx >= 0 && nfx < h && nfy >= 0 && nfy < w && matrix[nfx][nfy] == '.') {
                        matrix[nfx][nfy] = '*';
                        fireQueue.add(new int[]{nfx, nfy});
                    }
                }
            }

            // 2. 플레이어 이동
            int playerSize = playerQueue.size();
            for (int i = 0; i < playerSize; i++) {
                int[] player = playerQueue.poll();
                int px = player[0], py = player[1], time = player[2];

                // 가장자리에 도달하면 탈출 성공
                if (px == 0 || px == h - 1 || py == 0 || py == w - 1) {
                    return time + 1;
                }

                // 네 방향으로 이동 시도
                for (int[] dir : directions) {
                    int npx = px + dir[0], npy = py + dir[1];
                    // 이동 가능 조건: 범위 내, 방문 X, 빈 공간
                    if (npx >= 0 && npx < h && npy >= 0 && npy < w && !visited[npx][npy] && matrix[npx][npy] == '.') {
                        visited[npx][npy] = true;
                        playerQueue.add(new int[]{npx, npy, time + 1});
                    }
                }
            }
        }
        return -1; // 탈출 불가능한 경우
    }
}
