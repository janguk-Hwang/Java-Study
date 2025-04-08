import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String n = br.readLine();
        System.out.println(nextPalindrome(n));
    }

    public static String nextPalindrome(String n) {
        int len = n.length();
        boolean isOdd = (len % 2 == 1); // 홀수 판정
        // 앞 절반 + 중앙자리만큼 자르기
        String left = n.substring(0, (len + 1) / 2);
        // 거울처럼 복사
        String mirrored = mirror(left, isOdd);

        // 복사한 값이 n보다 크면 반환하여 바로 종료
        if (mirrored.compareTo(n) > 0) {
            return mirrored;
        }
        // 그렇지 않은 경우 앞부분을 증가시키면서
        else {
            // left + 1
            left = addOne(left);
            // 자릿수 증가했는지 확인 ex) 999 -> 1000
            if (left.length() > (len + 1) / 2) {
                len += 1;
                isOdd = (len % 2 == 1);
                left = left.substring(0, (len + 1) / 2);  // 앞에서 필요한 만큼만 자르기
            }
            return mirror(left, isOdd);
        }
    }

    // 좌측을 기준으로 팰린드롬 만들기
    public static String mirror(String left, boolean isOdd) {
        StringBuilder sb = new StringBuilder(left);
        if (isOdd) sb.setLength(sb.length() - 1); // 중간 문자 제외하고 뒤집음
        return left + sb.reverse().toString();
    }

    // 좌측 부분에 +1하기
    public static String addOne(String s) {
        StringBuilder sb = new StringBuilder(s);
        int i = s.length() - 1;     // 자리 수
        // 현재 자리가 9가 아니면 1증가, 현재 자리가 9이면 0으로 바꾸고 앞자리로 이동
        while (i >= 0) {
            if (sb.charAt(i) != '9') {
                sb.setCharAt(i, (char)(sb.charAt(i) + 1));
                break;
            }
            else {
                sb.setCharAt(i, '0');
                i--;
            }
        }
        // 모든 자리가 9였던 경우 ex) 999 -> 1000
        if (i < 0) sb.insert(0, '1');
        return sb.toString();   // 문자열로 변환해서 반환
    }
}
