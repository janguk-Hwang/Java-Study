import java.util.*;

// 한 턴에 두 수레가 이동할 수 있는 경우의 수 4x4(최대)를 진행
// 1-3, 2-4
class Solution {
    static int n, m;
    static boolean[][] visitedR, visitedB;
    static int min;
    static int eRx, eRy, eBx, eBy;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        min = Integer.MAX_VALUE;
        visitedR = new boolean[n][m];
        visitedB = new boolean[n][m];
        int sRx=0; int sRy=0; int sBx=0; int sBy=0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(maze[i][j] == 1) {sRx = i; sRy = j;}
                else if(maze[i][j] == 2) {sBx = i; sBy = j;}
                else if(maze[i][j] == 3) {eRx = i; eRy = j;}
                else if(maze[i][j] == 4) {eBx = i; eBy = j;}
            }
        }
        visitedR[sRx][sRy] = true;
        visitedB[sBx][sBy] = true;
        func(sRx, sRy, sBx, sBy, 0, maze);
        return min == Integer.MAX_VALUE ? 0 : min;
    }
    
    public static void func(int rx, int ry, int bx, int by, int depth, int[][] maze){
        // 종료 조건
        if(rx == eRx && ry == eRy && bx == eBx && by == eBy){
            min = Math.min(min, depth);
            return;
        }
        // 가지치기
        if(depth >= min) return;
        // 두 수레의 도착 여부
        boolean rArrived = (rx == eRx && ry == eRy);
        boolean bArrived = (bx == eBx && by == eBy);
        // 이동 가능 좌표 탐색(도착한 수레는 탐색 x)
        int[][] nR = rArrived ? new int[][]{{rx, ry}} : isPossibleNext(rx, ry, visitedR, maze);
        int[][] nB = bArrived ? new int[][]{{bx, by}} : isPossibleNext(bx, by, visitedB, maze);
        for(int[] nxtR : nR){
            for(int[] nxtB : nB){
                // 다음 이동할 좌표
                int nrx = nxtR[0], nry = nxtR[1];
                int nbx = nxtB[0], nby = nxtB[1];
                // 동시에 두 수레를 같은 칸으로 움직일 수 없습니다
                if(nrx == nbx && nry == nby) continue;
                // 수레끼리 자리를 바꾸며 움직일 수 없습니다.
                if(nrx == bx && nry == by && nbx == rx && nby == ry) continue;
                
                if(!rArrived) visitedR[nrx][nry] = true;
                if(!bArrived) visitedB[nbx][nby] = true;
                func(nrx, nry, nbx, nby, depth+1, maze);
                if(!rArrived) visitedR[nrx][nry] = false;
                if(!bArrived) visitedB[nbx][nby] = false;
            }
        }    
    }
    
    // 이동 가능한 위치들을 배열에 담아 반환
    public static int[][] isPossibleNext(int x, int y, boolean[][] visited, int[][] maze){
        int cnt = 0;
        int[][] temp = new int[4][2];
        for(int d=0; d<4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(nx >= 0 && nx < n && ny >= 0 && ny < m && maze[nx][ny] != 5 && !visited[nx][ny]){
                temp[cnt][0] = nx;
                temp[cnt][1] = ny;
                cnt++;
            }
        }
        int[][] returnTemp = new int[cnt][2];
        for(int i=0; i<cnt; i++) returnTemp[i] = temp[i];
        return returnTemp;
    }
}


















