import java.io.*;
import java.util.*;

// 같은 수열이 붙어있으면 나쁜 수열 아니면 좋은 수열
// 1, 2, 3 중 작은 수부터 붙여 나간다.
public class Main {
    static int n;
    static boolean flag;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        backtracking("");
    }

    public static void backtracking(String s){
        if(flag) return;
        // 종료 조건
        if(s.length() == n){
            System.out.print(s);
            flag = true;
            return;
        }
        for(int i=1; i<=3; i++){
            String newS = s + i;
            if(isGood(newS)) backtracking(newS);
        }
    }

    public static boolean isGood(String str){
        int len = str.length();
        for(int i=1; i<=len/2; i++){
            // 뒤에서 i만큼 뗀 바로 앞 부분
            String s1 = str.substring(len - 2 * i, len - i);
            // 맨 끝에서 i만큼 뗀 부분
            String s2 = str.substring(len - i, len);
            if(s1.equals(s2)) return false;
        }
        return true;
    }
}