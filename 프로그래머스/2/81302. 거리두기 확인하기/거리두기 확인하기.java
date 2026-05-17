// p(응시자가 앉아있는 자리), o(빈 테이블), x(파티션)
import java.util.*;

class Solution {
    static Queue<int[]> q;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[] rst = new int[5];
    public int[] solution(String[][] places) {
        for(int i=0; i<5; i++){
            if(isSatisfy(places[i])) rst[i] = 1;
            else rst[i] = 0;
        }
        return rst;
    }
    
    static boolean isSatisfy(String[] arr){
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(arr[i].charAt(j) == 'P'){
                    if(!bfs(arr, i, j)) return false;
                }
            }
        }
        return true;
    }
    
    static boolean bfs(String[] arr, int r, int c){
        q = new LinkedList<>();
        visited = new boolean[5][5];
        q.offer(new int[]{r, c, 0});
        visited[r][c] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int nr = cur[0];
            int nc = cur[1];
            int dist = cur[2];
            if(dist > 0 && arr[nr].charAt(nc) == 'P') return false;
            if(dist == 2) continue;
            for(int d=0; d<4; d++){
                int nnr = nr + dr[d];
                int nnc = nc + dc[d];
                if(nnr >= 0 && nnr < 5 && nnc >= 0 && nnc < 5 && !visited[nnr][nnc]){
                    if(arr[nnr].charAt(nnc) != 'X'){
                        visited[nnr][nnc] = true;
                        q.offer(new int[]{nnr, nnc, dist + 1});
                    }
                }
            }
        }
        return true;
    }
}