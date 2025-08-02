import java.io.*;
import java.util.*;

// 회문 = 팰린드롬 = 대칭되는 문자열
// 회문은 아니지만 한 문자를 제거하면 회문이 되는 문자열 = 유사회문
// 회문(0) or 유사회문(1) or 일반 문자열 판단(2)
// 맨 앞, 맨 뒤 문자를 비교해가면서 중앙으로 한 칸씩 이동해간다. 모두 동일하면 회문
// 유사회문은 한 번의 데스 카운트가 있는 느낌
// 문자 비교가 최초로 다른 경우, 두 문자에서 다음 문자와 같은 것이 있는지 서로 확인한다.
// 만약 없다면 일반 문자열
public class Main {
    static int t;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            String str = br.readLine();
            sb.append(isPalindrome(str)).append("\n");
        }
        System.out.print(sb);
    }

    public static int isPalindrome(String str){
        int left = 0;
        int right = str.length() - 1;
        while(left < right){
            if(str.charAt(left) == str.charAt(right)){
                left++;
                right--;
                continue;
            }
            boolean flagLeftRemove = true;
            int tempL = left + 1;
            int tempR = right;
            while(tempL < tempR){
                if(str.charAt(tempL) != str.charAt(tempR)){
                    flagLeftRemove = false;
                    break;  // 이미 회문될 수 없으므로 종료
                }
                tempL++;
                tempR--;
            }

            boolean flagRightRemove = true;
            tempL = left;
            tempR = right - 1;
            while(tempL < tempR){
                if(str.charAt(tempL) != str.charAt(tempR)){
                    flagRightRemove = false;
                    break;
                }
                tempL++;
                tempR--;
            }
            // 왼쪽 문자 삭제 or 오른쪽 문자 삭제한 경우 중 회문이 가능하다면 유사회문
            if(flagLeftRemove || flagRightRemove) return 1;
            else return 2;
        }
        return 0;
    }
}