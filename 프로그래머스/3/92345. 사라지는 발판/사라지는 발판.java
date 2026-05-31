// 움직일 곳이 없는 경우, 같은 발판에 서있다가 상대가 이동한 경우
// 게임 이론? 최적의 플레이?
// 이길 수 있는 플레이어는 최대한 빨리 승리하도록 플레이하고, 질 수밖에 없는 플레이어는 최대한 오래 버티도록 플레이합니다.

class Solution {
    class State{
        boolean flag;
        int dist;
        public State(boolean flag, int dist){
            this.flag = flag;
            this.dist = dist;
        }
    }
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        return dfs(board, aloc[0], aloc[1], bloc[0], bloc[1]).dist;
    }
    
    private State dfs(int[][] board, int ax, int ay, int bx, int by){
        // 서있는 발판이 사라진 경우(같이 있다가 상대가 이동)
        if(board[ax][ay] == 0) return new State(false, 0);
        
        boolean isWin = false;
        int minWinDist = Integer.MAX_VALUE;
        int maxLoseDist = 0;
        for(int d=0; d<4; d++){
            int nx = ax + dx[d];
            int ny = ay + dy[d];
            if(nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length || board[nx][ny] == 0) continue;
            // 이동할 수 있으면 현재 발판을 0으로 만들고 이동
            board[ax][ay] = 0;
            State state = dfs(board, bx, by, nx, ny);   // 상대방이 탐색
            board[ax][ay] = 1;
            
            if(!state.flag){
                isWin = true;
                minWinDist = Math.min(minWinDist, state.dist + 1);
            }
            else maxLoseDist = Math.max(maxLoseDist, state.dist + 1);
        }
        return isWin ? new State(true, minWinDist) : new State(false, maxLoseDist);
    }
}















