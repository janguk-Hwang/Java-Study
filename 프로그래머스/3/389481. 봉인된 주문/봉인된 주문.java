import java.io.*;
import java.util.*;
// 1. 글자 수가 적은 주문부터 먼저 기록된다.
// 2. 글자 수가 같다면, 사전 순서대로 기록된다.
// 삭제 전 bans의 주문들의 순서를 계산하고 n보다 앞에 오는 주문들마다 n을 증가
// 증가된 n을 알아내고 삭제되기 전의(문제에서 제시된 정렬 기준에 따른) 증가된 n번째 문자열을 출력
class Solution {
    static long[] banArr;
    static final int alpabetNum = 26;
    static int wordLen;
    public String solution(long n, String[] bans) {
        int banLen = bans.length;
        long[] order = new long[banLen];
        banArr = new long[banLen];
        for(int i=0; i<banLen; i++){
            wordLen = bans[i].length();
            long ord = 0;   // bans[i]보다 글자 수가 적은 주문의 개수
            // 26개, 26^2개, 26^3개 ...
            long temp = 26;
            for(int j=1; j<wordLen; j++){
                ord += temp;
                temp *= alpabetNum;
            }
            
            temp = 1;
            temp = (long)Math.pow(alpabetNum, wordLen-1);
            for(int j=0; j<wordLen; j++){
                ord += (bans[i].charAt(j) - 'a') * temp;
                temp /= alpabetNum;
            }
            // ord에는 현재 단어의 번호(순서)를 의미
            
            // bans[i] 주문의 삭제 전 번호(순서)
            banArr[i] = ord + 1;
            
        }
        Arrays.sort(banArr);
        for(int j=0; j<banLen; j++){
            if(banArr[j] <= n) n++;
            else break;
        }

        // 증가된 n번째 주문
        int len = 1;    // 주문의 길이
        long temp = 26;
        while(n > temp){
            n -= temp;
            len++;
            temp *= alpabetNum;
        }
        
        n--;

        char[] spell = new char[len];
        for(int i=len-1; i>=0; i--){
            spell[i] = (char)('a' + (n % 26));
            n /= alpabetNum;
        }
        return new String(spell);
    }
}