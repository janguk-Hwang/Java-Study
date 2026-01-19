import java.io.*;
import java.util.*;

public class Main {
    static int n, len;
    static int[] cnt;
    static char[] word;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            String str = br.readLine();
            len = str.length();
            cnt = new int[26];
            for(int j=0; j<len; j++) cnt[str.charAt(j) - 'a']++;
            word = new char[len];
            backtracking(0);
        }
        System.out.print(sb);
    }

    static void backtracking(int depth){
        // 종료 조건
        if(depth == len){
            sb.append(word).append("\n");
            return;
        }
        // 가능한 선택
        for(int i=0; i<26; i++){
            if(cnt[i] == 0) continue;
            cnt[i]--;
            word[depth] = (char) ('a' + i);
            backtracking(depth + 1);
            cnt[i]++;
        }
    }
}