import java.io.*;
import java.util.*;

// 문자열 S안에서 문자열 W의 순열 중 하나가 '부분 문자열'로 들어있는 모든 경우의 수
// W와 길이가 같은 경우만 검사 => 앞, 뒤로 어떤 문자가 붙으면 중복으로 카운트되기 때문
// 순열이기 때문에 문자열 내부에 있는 알파벳들의 출현 횟수가 같으면 들어있는 것이다. => 카운트 배열 사용
public class Main {
    static int[] wCnt, sCnt;
    static int wLen, sLen;
    static String w, s;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        wLen = Integer.parseInt(st.nextToken());
        sLen = Integer.parseInt(st.nextToken());
        w = br.readLine();
        s = br.readLine();
        wCnt = new int[52];
        sCnt = new int[52];
        for(char c : w.toCharArray()) func(c, wCnt, 1);
        int cnt = 0;
        int len = 0;
        for(int i=0; i<sLen; i++){
            func(s.charAt(i), sCnt, 1);
            len++;
            // w의 길이와 윈도우의 길이가 최소한 같아야 부분 문자열 가능
            if(len == wLen){
                if(Arrays.equals(wCnt, sCnt)) cnt++;
                func(s.charAt(i - wLen + 1), sCnt, -1);
                len--;
            }
        }
        System.out.print(cnt);
    }

    // 문자 c를 카운트 배열에서 감소시키는 함수
    static void func(char c, int[] arr, int diff){
        // 소문자는 0~25, 대문자는 26~51
        if('a' <= c && c <= 'z') arr[c - 'a'] += diff;
        else arr[c - 'A' + 26] += diff;
    }
}