import java.io.*;
import java.util.*;

// 투 포인터로 right가 계속 증가, 대신 알파벳의 종류가 n개를 넘으면 알파벳의 종류가 n개보다 작아질 때까지 left++
// 위 과정을 right가 문자열 끝까지 이동하면서 진행하고 매번 가장 긴 문자열의 길이 갱신
public class Main {
    static int[] cnt;       // 각 알파벳의 출현 횟수
    static int n, max;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        cnt = new int[26];

        max = 0;
        int type = 0;   // left ~ right 사이의 문자열에 있는 알파벳의 종류 수
        int left = 0;
        for(int right = 0; right<str.length(); right++){
            char c = str.charAt(right);
            if(cnt[c - 'a'] == 0) type++;
            cnt[c - 'a']++;
            while(type > n){
                cnt[str.charAt(left) - 'a']--;
                if(cnt[str.charAt(left) - 'a'] == 0) type--;
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        System.out.print(max);
    }
}