// 첫 번째 행을 기준으로 경우 나누기
// 첫 번째 행을 뒤집을 것인가 뒤집지 않을 것인가
// 첫 번째 행을 뒤집는다면 목표와 동일하게 만들기 위해 열을 뒤집어야 한다.
// 첫 번재 행을 뒤집지 않는 경우도 마찬가지로 목표와 동일하게 만들기 위해 열을 뒤집어야 한다.

class Solution {
    public int solution(int[][] beginning, int[][] target) {
        int r = beginning.length;
        int c = beginning[0].length;
        int[][] temp = new int[r][c];
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                temp[i][j] = beginning[i][j] ^ target[i][j];
            }
        }
        // 첫 번째 행에서 초기와 목표 상태에서 다른 칸의 개수와 같은 칸의 개수
        int needChange = 0;
        int NNeedChange = 0;
        for(int i=0; i<c; i++){
            if(temp[0][i] == 1) needChange++;
            else NNeedChange++;
        }
        
        int not_reverse_first_row = needChange;
        int reverse_first_row = NNeedChange + 1;
        // 2 ~ 마지막 행까지 검사
        for(int i=1; i<r; i++){
            boolean isSame_first_row = true;
            boolean notSame_first_row = true;
            for(int j=0; j<c; j++){
                if(temp[i][j] != temp[0][j]) isSame_first_row = false;
                if(temp[i][j] == temp[0][j]) notSame_first_row = false;
            }
            if(!isSame_first_row && !notSame_first_row) return -1;
            if(isSame_first_row) reverse_first_row += 1;
            else not_reverse_first_row += 1;
        }
        return Math.min(reverse_first_row, not_reverse_first_row);
    }
}
















