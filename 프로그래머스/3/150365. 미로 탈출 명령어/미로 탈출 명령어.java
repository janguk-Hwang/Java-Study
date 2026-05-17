// k와 최단 거리의 홀짝성으로 사전에 가능성 확인
// d, l, r, u가 사전순. 하좌우상
import java.util.*;

class Solution {
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    static char[] dir = {'d', 'l', 'r', 'u'};
    static StringBuilder sb = new StringBuilder();
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        // 최단 거리
        int dist = Math.abs(x - r) + Math.abs(y - c);
        if(dist > k || (k - dist) % 2 != 0) return "impossible";
        int tempX = x; int tempY = y;
        for(int i=0; i<k; i++){
            for(int d=0; d<4; d++){
                int nx = tempX + dx[d];
                int ny = tempY + dy[d];
                if(nx >= 1 && nx <= n && ny >= 1 && ny <= m){
                    int nDist = Math.abs(nx - r) + Math.abs(ny - c);
                    if(nDist <= k - i - 1){
                        sb.append(dir[d]);
                        tempX = nx;
                        tempY = ny;
                        break;
                    }
                }
            }
        }
        return sb.toString();
    }
}