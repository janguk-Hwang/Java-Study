import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 알파벳 출현 횟수를 담을 정수형 배열
        Scanner sc = new Scanner(System.in);
        int[] number = new int[26];
        int maximum = 0;
        String S = sc.next();
        S = S.toUpperCase();
        for (int i = 0; i < S.length(); i++) {
            // A-Z이면
            if ((S.charAt(i) >= 65) && (S.charAt(i) <= 90)) {
                // 배열의 숫자 ++;
                // 수정된 부분: 해당 알파벳에 해당하는 인덱스 값을 증가시킴
                number[S.charAt(i) - 65]++;
            }
        }
        char ch = '?';
        for (int j = 0; j < 26; j++) {
            if (number[j] > maximum) {
                maximum = number[j];
                ch = (char) (j + 65);
            } else if (maximum == number[j]) {
                ch = '?';
            }
        }
        System.out.println(ch);
    }
}
