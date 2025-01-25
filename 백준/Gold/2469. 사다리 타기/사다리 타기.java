import java.io.*;
import java.util.*;

public class Main {
    static int n, k;    //n은 알파벳의 개수, k는 레이어의 개수
    static char[][] map;     //사다리 게임의 상태를 저장하는 2차원 배열
    static char[] top;   //최상단 알파벳의 초기 상태를 저장
    static int targetLayer;     //'?'가 위치한 레이어를 저장(?가 위치한 레이어)
    static char[] want;     //목표 상태를 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        map = new char[k][n - 1];
        want = new char[n];
        top = new char[n];

        //입력
        String s1 = br.readLine();
        for (int i = 0; i < s1.length(); i++) {
            want[i] = s1.charAt(i);
            top[i] = (char)('A' + i);
        }

        //각 층의 사다리 정보 입력
        for (int i = 0; i < k; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '?') {   //'?'인 경우 해당 레이어를 targetLayer로 저장
                    targetLayer = i;
                    Arrays.fill(map[i], '?'); // 해당 레이어를 '?'로 채움
                    continue;
                }
                map[i][j] = s.charAt(j); // '-' 또는 '*' 저장
            }
        }

        //위에서 아래로 진행
        for (int i = 0; i < targetLayer; i++) {
            makeNext(i);
        }

        //아래에서 위로 진행
        for (int i = k - 1; i > targetLayer; i--) {
            makePrev(i);
        }

        StringBuilder sb = new StringBuilder();

        //'?' 층에서 알파벳 간의 연결 상태 결정
        for (int i = 0; i < n - 1; i++) {
            if (top[i] != want[i]) { // top과 want가 다르면 교환이 필요
                if (top[i] != want[i + 1]) { // 바로 옆 알파벳과도 다르면 실패
                    sb.delete(0, sb.length()); // 이전 결과 초기화
                    for (int j = 0; j < n - 1; j++) {
                        sb.append("x"); // 모든 경로를 'x'로 채움
                    }
                    break;
                }
                sb.append("-"); // 교환이 필요한 경우 '-'
                i += 1; // 교환이 이루어지므로 다음 인덱스로 건너뜀
                if (i < n - 1) {
                    sb.append("*"); // 교환 이후 '*' 추가
                }
                continue;
            }
            sb.append("*"); // 교환이 필요 없는 경우 '*'
        }
        System.out.println(sb.toString()); // 결과 출력
    }

    //(top → want)으로의 상태 갱신
    public static void makePrev(int index) {
        for (int i = 0; i < n - 1; i++) {
            char now = map[index][i];
            if (now == '-') { // '-'일 경우 교환
                char prev = want[i];
                want[i] = want[i + 1];
                want[i + 1] = prev;
            }
        }
    }

    //(top → want)로의 상태 갱신
    public static void makeNext(int index) {
        for (int i = 0; i < n - 1; i++) {
            char now = map[index][i];
            if (now == '-') { // '-'일 경우 교환
                char prev = top[i + 1];
                top[i + 1] = top[i];
                top[i] = prev;
            }
        }
    }
}
