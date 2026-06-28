// 2^31-1이므로 배열을 만들 수 없음열을 만들 수 없음
import java.util.*;

class Solution {
    public int solution(int n, int[][] data){
        int rst = 0;
        // x좌표를 기준으로 오름차순, x좌표가 같으면 y좌표를 기준으로 오름차순
        Arrays.sort(data, (a, b) -> {
           if(a[0] == b[0]) return a[1] - b[1];
           return a[0] - b[0];
        });
        // 첫 번째 쐐기 선택
        for(int i=0; i<n; i++){
            // 두 번째 쐐기 선택
            for(int j=i+1; j<n; j++){
                // x좌표나 y좌표가 같으면 넓이가 0이므로 continue;
                if(data[i][0] == data[j][0] || data[i][1] == data[j][1]) continue;  
                boolean flag = true;
                // i < k < j
                for(int k=i+1; k<j; k++){
                    if(data[i][0] < data[k][0] && data[j][0] > data[k][0] &&
                       // y좌표는 i, j의 대소관계가 정해져있지 않음
                       Math.min(data[i][1], data[j][1]) < data[k][1] && 
                       Math.max(data[i][1], data[j][1]) > data[k][1]){
                        flag = false;
                        break;  // 내부에 하나의 쐐기라도 있으면 break
                    }
                }
                if(flag) rst++;
            }
        }
        return rst;
    }
}