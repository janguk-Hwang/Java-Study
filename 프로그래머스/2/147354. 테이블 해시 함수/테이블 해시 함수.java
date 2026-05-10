import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        // col번째 컬럼 값 기준으로 오름차순 정렬, 같다면 PK 값으로 내림차순 정렬
        Arrays.sort(data, (a, b) -> {
           return a[col-1] != b[col-1] ? a[col-1] - b[col-1] : b[0] - a[0];
        });
        int rst = 0;
        for(int i=row_begin-1; i<=row_end-1; i++){
            int temp = 0;
            for(int j=0; j<data[i].length; j++) temp += data[i][j] % (i+1);
            rst = temp ^ rst;
        }
        return rst;
    }
}