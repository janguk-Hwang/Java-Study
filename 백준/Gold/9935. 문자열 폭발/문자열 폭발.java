import java.io.*;
import java.util.StringTokenizer;

// 입력으로 주어지는 문자열에서 문자를 하나씩 sb에 쌓는다.
// sb의 길이가 폭발 문자열만큼이 되고, 마지막 문자가 폭발 문자열의 마지막 문자와 같으면
// 진짜로 sb에 폭발 문자열이 있는지 검사한다.
// 폭발 문자열이 있으면 폭발 문자열이 있는 뒷부분을 잘라낸다.
// sb에는 폭발 문자열이 아니라서 제거되지 않은 문자들이 남아있고 이후에 아직 처리되지 않은 문자들이
// sb에 이어서 쌓이면서 또 폭발 문자열이 되면 제거된다.
public class Main {
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String input = br.readLine();
        String boom = br.readLine();
        int boomLen = boom.length();
        char boomLast = boom.charAt(boomLen - 1);
        for(int i=0; i<input.length(); i++){
            sb.append(input.charAt(i));
            if(sb.length() >= boomLen && sb.charAt(sb.length() - 1) == boomLast){
                boolean isSame = true;
                for(int j=0; j<boomLen-1; j++){
                    if(sb.charAt(sb.length() - boomLen + j) != boom.charAt(j)){
                        isSame = false;
                        break;
                    }
                }
                if(isSame) sb.setLength(sb.length() - boomLen);
            }
        }
        System.out.print(sb.length() == 0 ? "FRULA" : sb);
    }
}