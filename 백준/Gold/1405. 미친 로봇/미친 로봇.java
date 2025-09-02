import java.io.*;
import java.util.*;

// 문제 설명에서 예시를 보니 같은 지점을 2번 이상 도착하면 단순하지 않다.
// 이동 경로를 선으로 그어봤을 때, 사이클이 한 붓 그리기같은 느낌
// 한 붓 그리기가 될 확률을 출력
// dfs로 진행나가면서 확률은 어떻게 계산되어야 할까?
// 확률을 계속 곱해나가면 그 확률은 해당 경로로 이동하기 위한 확률을 의미한다.
// 방문되지 않은 곳만 방문하기 때문에 단순하지 않은 경로는 총 단순할 확률에 누적되지 않는다.
public class Main {
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int n;
    static double rst = 0.0;
    static double[] probability = new double[4];
    static boolean[][] visited;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        for(int d=0; d<4; d++) probability[d] = Double.parseDouble(st.nextToken()) * 0.01;
        visited = new boolean[30][30];
        visited[15][15] = true;
        dfs(15, 15, 0, 1);
        System.out.print(rst);
    }

    public static void dfs(int r, int c, int depth, double tillNowProbability){
        // 종료 조건
        if(depth == n){
            rst += tillNowProbability;
            return;
        }
        // 가능한 선택
        for(int d=0; d<4; d++){
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr <= 0 || nr >= 30 || nc <=0 || nc >= 30) continue;
            if(!visited[nr][nc]){
                visited[nr][nc] = true;
                // 새로운 좌표, 이동 횟수+1, 확률을 계속 곱해나감
                dfs(nr, nc, depth + 1, tillNowProbability * probability[d]);
                visited[nr][nc] = false;
            }
        }
    }
}