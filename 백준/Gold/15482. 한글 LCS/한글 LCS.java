import java.io.*;
import java.util.*;

public class Main {
    static StringTokenizer st;
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        String s1 = st.nextToken();
        st = new StringTokenizer(br.readLine());
        String s2 = st.nextToken();
        n = s1.length(); m = s2.length();

        // 이전 행을 저장할 배열(prev)과 현재 행을 저장할 배열(curr)
        // 길이는 두 번째 문자열의 길이 m에 1을 더한 크기
        int[] prev = new int[m + 1];
        int[] curr = new int[m + 1];

        // 첫 번째 문자열을 기준으로 1부터 n까지 반복
        for (int i = 1; i <= n; i++) {
            // 두 번째 문자열을 기준으로 1부터 m까지 반복
            for (int j = 1; j <= m; j++) {
                // 두 문자열의 현재 문자들이 같다면
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    // 이전 행에서 왼쪽 대각선 값(prev[j - 1])에 1을 더함
                    curr[j] = prev[j - 1] + 1;
                }
                else {
                    // 두 값 중 큰 값을 선택
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }

            // 현재 행을 이전 행으로 업데이트
            // temp 배열을 사용하여 두 배열을 교환함으로써 공간을 절약
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        System.out.print(prev[m]);
    }
}
