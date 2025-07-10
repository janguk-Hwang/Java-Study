import java.io.*;
import java.util.StringTokenizer;

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
                for(int j=0; j<boomLen; j++){
                    if(sb.charAt(sb.length() - boomLen + j) != boom.charAt(j)){
                        isSame = false;
                        break;
                    }
                }
                if(isSame) sb.setLength(sb.length() - boomLen);
            }
        }
        System.out.println(sb.length() == 0 ? "FRULA" : sb.toString());
    }
}