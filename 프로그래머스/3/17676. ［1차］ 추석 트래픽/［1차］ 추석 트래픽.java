import java.text.*;
import java.util.*;

// 임의 시간부터 1초간 처리하는 요청의 최대 개수를 출력.
// lines는 응답완료시간을 기준으로 오름차순 정렬되어 주어진다.
// 각 요청이 끝나는 시간마다 해당 시점에 겹치는 요청의 수를 센다.
// 어떤 요청의 중간에서부터 1초간 처리하는 요청의 최대 수는 항상 어떤 요청이 끝나는 시점부터
// 1초간 처리하는 요청의 최대 수보다 클 수 없다.
// 그리디
class Solution {
    static int n, max;
    static int[] cnt;
    static SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");
    public int solution(String[] lines) throws Exception {
        n = lines.length;
        cnt = new int[n];
        max = 0;
        for(int i=0; i<n; i++){
            // 응답완료시간을 밀리초로 변환
            long curEnd = format.parse(lines[i].split(" ")[1]).getTime();
            for(int j=i; j<n; j++){
                // 처리시간은 시작시간과 끝시간을 포함
                String[] next = lines[j].split(" ");
                // j번째 요청의 응답완료시간
                long nextEnd = format.parse(next[1]).getTime();
                // 끝에 s를 제거한 처리시간
                double sec = Double.parseDouble(next[2].substring(0, next[2].length() - 1));
                // sec는 1초 단위이므로 1000을 곱하고 
                long nextStart = (long)(nextEnd - sec * 1000 + 1);
                // i번째 요청의 끝시간부터 1초안에 시작하는 요청들의 수를 
                if(curEnd + 1000 > nextStart){
                    cnt[i]++;
                    max = Math.max(max, cnt[i]);
                }
            }
        }
        return max;
    }
}
