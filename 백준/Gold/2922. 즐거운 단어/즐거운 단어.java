import java.io.*;
import java.util.*;

// 관리해야할 것은 l이 포함되었는지, 자음, 모음이 연속 몇 번 나온 상태인지, 현재 인덱스, 현재까지의 경우의 수
// consonant 자음, vowel 모음, 정답은 2^63-1을 넘지 않는다.
public class Main {
    static String word;
    static int len;
    static long ans;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        word = br.readLine();
        len = word.length();
        backtracking(false, 0, 0, 0, 1L);
        System.out.print(ans);
    }

    static void backtracking(boolean hasL, int consonant, int vowel, int idx, long rst){
        if(consonant >= 3 || vowel >= 3) return;
        if(idx == len){
            if(hasL) ans += rst;
            return;
        }
        char c = word.charAt(idx);
        if(c == '_'){
            // 모음
            backtracking(hasL, 0, vowel + 1, idx + 1, rst * 5);
            // 자음(l x)
            backtracking(hasL, consonant + 1, 0, idx + 1, rst * 20);
            // 자음(l)
            backtracking(true, consonant + 1, 0, idx + 1, rst);
        }
        else{
            if(c == 'A' || c == 'E' || c == 'I' || c == 'O' || c=='U') backtracking(hasL, 0, vowel + 1, idx + 1, rst);
            else backtracking(hasL || c == 'L', consonant + 1, 0, idx + 1, rst);
        }
    }
}