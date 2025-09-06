import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[] visited = new boolean[25];
    static char[][] matrix = new char[5][5];
    static int rst = 0;
    static int[] sevenPrincess = new int[7];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        for(int i=0; i<5; i++) matrix[i] = br.readLine().toCharArray();
        combination(0, 0, 0);
        System.out.print(rst);
    }

    public static void combination(int idx, int depth, int yCnt){
        if(yCnt >= 4) return;
        // 종료 조건
        if(depth == 7){
            for(int i=0; i<25; i++){
                if(visited[i]){
                    if(isPossible(i/5, i%5)){
                        rst++;
                    }
                    break;
                }
            }
            return;
        }
        // 가능한 모든 경우 선택
        for(int i=idx; i<25; i++){
            visited[i] = true;
            if(matrix[i/5][i%5] == 'Y') combination(i+1, depth + 1, yCnt+1);
            else combination(i+1, depth+1, yCnt);
            visited[i] = false;
        }
    }

    public static boolean isPossible(int r, int c){
        boolean[][] visited1 = new boolean[5][5];
        Queue<int[]> q = new LinkedList<>();
        visited1[r][c] = true;
        q.add(new int[]{r, c});
        int cnt = 1;    // 선택된 칠공주의 수
        while(!q.isEmpty()){
            int[] now = q.poll();
            for(int d=0; d<4; d++){
                int nr = now[0] + dr[d];
                int nc = now[1] + dc[d];
                int next = nr * 5 + nc;
                if(nr < 0 || nr >= 5 || nc < 0 || nc >= 5) continue;
                // 방문되지 않았고 조합에서 선택한 칠공주 중에 한 명이라면
                if(!visited1[nr][nc] && visited[next]){
                    visited1[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                    cnt++;
                }
            }
        }
        if(cnt == 7) return true;
        return false;
    }
}