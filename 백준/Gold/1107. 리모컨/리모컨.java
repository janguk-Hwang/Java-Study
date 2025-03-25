import java.util.*;
import java.io.*;

public class Main {
    static boolean[] broken = new boolean[10];
    static StringTokenizer st;
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 이동하려는 채널
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken()); // 고장난 버튼 개수

        // 고장난 버튼 정보 입력
        if (m > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }

        int minPress = Math.abs(n - 100); // +, -만 사용한 경우

        // 모든 가능한 채널 번호를 시도 (0 ~ 999999)
        for (int i = 0; i <= 999999; i++) {
            int len = getPressCount(i);
            if (len > 0) {
                int pressCount = len + Math.abs(i - n);
                minPress = Math.min(minPress, pressCount);
            }
        }

        System.out.println(minPress);
    }

    // 숫자 버튼을 눌러서 해당 채널로 이동할 수 있는지 확인하고, 가능하면 길이 반환
    static int getPressCount(int num) {
        if (num == 0) {
            return broken[0] ? 0 : 1;
        }

        int len = 0;
        while (num > 0) {
            if (broken[num % 10]) return 0;
            len++;
            num /= 10;
        }
        return len;
    }
}
