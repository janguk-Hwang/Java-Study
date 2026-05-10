import java.io.*;
import java.util.*;

// 선분의 중점으로 테두리인지 판단
class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<int[]> q = new LinkedList<>();
    static boolean[][] visited = new boolean[51][51];
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        q.offer(new int[]{characterX, characterY, 0});
        visited[characterX][characterY] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0]; int y = cur[1];
            int dist = cur[2];
            if(x == itemX && y == itemY) return dist;
            for(int d=0; d<4; d++){
                int nx = x + dx[d];
                int ny = y + dy[d];
                if(nx >= 0 && nx <= 50 && ny >= 0 && ny <= 50 && !visited[nx][ny]){
                    if(isBorder(x, y, nx, ny, rectangle)){
                        visited[nx][ny] = true;
                        q.offer(new int[]{nx, ny, dist + 1});
                    }
                }
            }
        }
        return 0;
    }
    
    public static boolean isBorder(int x1, int y1, int x2, int y2, int[][] rectangle){
        double midX = (x1 + x2) / 2.0;
        double midY = (y1 + y2) / 2.0;
        boolean flag = false;
        for(int[] rect : rectangle){
            int nx1 = rect[0], ny1 = rect[1], nx2 = rect[2], ny2 = rect[3];
            // 중점이 직사각형의 내부에 위치한다면 테두리가 아님
            if(nx1 < midX && nx2 > midX && ny1 < midY && ny2 > midY) return false;
            // 수직 테두리
            if((midX == nx1 || midX == nx2) && (ny1 <= midY && midY <= ny2)) flag = true;
            // 수평 테두리
            if((midY == ny1 || midY == ny2) && (nx1 <= midX && midX <= nx2)) flag = true;
        }
        return flag;
    }
}