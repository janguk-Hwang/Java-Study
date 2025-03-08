import java.util.*;
 
//padding을 해줘서 테두리를 추가한 후, BFS로 외곽과 닿아있는 부분을 감지한다.
//각 query 실행 -> bfs를 통해서 경계 갱신.
class Solution {
    static int[][] dires = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
 
    public int solution(String[] storage, String[] requests) {
        int H = storage.length + 2;
        int W = storage[0].length() + 2;
 
        // padding 배열 초기화
        char[][] arr = new char[H][W];
        for (int i = 0; i < H; i++) {
            Arrays.fill(arr[i], '-');
        }
        // 배열 업데이트
        for (int i = 1; i < H - 1; i++) {
            for (int j = 1; j < W - 1; j++) {
                arr[i][j] = storage[i - 1].charAt(j - 1);
            }
        }
 
        // 쿼리
        for (int i = 0; i < requests.length; i++) {
            String request = requests[i];
            char target = request.charAt(0);
            List<int[]> removeList = new ArrayList<>();
 
            // 2인 경우 전부 제거
            if (request.length() == 2) {
                for (int j = 0; j < H; j++) {
                    for (int k = 0; k < W; k++) {
                        if (arr[j][k] == target) {
                            arr[j][k] = '-';
                        }
                    }
                }
            } else {
                // 1인 경우 주변에 하나라도 boundary
                boolean[][] boundary = bfs(arr, H, W);
                for (int j = 1; j < H - 1; j++) {
                    for (int k = 1; k < W - 1; k++) {
                        if (arr[j][k] == target) {
                            for (int l = 0; l < dires.length; l++) {
                                int[] dire = dires[l];
                                int ni = j + dire[0], nj = k + dire[1];
                                if (boundary[ni][nj]) {
                                    removeList.add(new int[]{j, k});
                                    break;
                                }
                            }
                        }
                    }
                }
                for (int j = 0; j < removeList.size(); j++) {
                    int[] loc = removeList.get(j);
                    arr[loc[0]][loc[1]] = '-';
                }
            }
        }
        // 남은 화물 개수 세기
        int answer = 0;
        for (int i = 1; i < H - 1; i++) {
            for (int j = 1; j < W - 1; j++) {
                if (arr[i][j] != '-') answer++;
            }
        }
        return answer;
    }
 
    public boolean[][] bfs(char[][] arr, int H, int W) {
        boolean[][] V = new boolean[H][W];
        ArrayDeque<int[]> Q = new ArrayDeque<>();
        Q.offer(new int[]{0, 0});
        V[0][0] = true;
 
        while (!Q.isEmpty()) {
            int[] cur = Q.poll();
            int x = cur[0], y = cur[1];
 
            for (int i = 0; i < dires.length; i++) {
                int[] d = dires[i];
                int nx = x + d[0], ny = y + d[1];
                if (nx >= 0 && nx < H && ny >= 0 && ny < W && arr[nx][ny] == '-' && !V[nx][ny]) {
                    V[nx][ny] = true;
                    Q.offer(new int[]{nx, ny});
                }
            }
        }
        return V;
    }
}
