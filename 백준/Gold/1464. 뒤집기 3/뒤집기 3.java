import java.io.*;
import java.util.*;

// i번째 문자는 지금까지의 문자열의 앞 또는 뒤에 붙을 수 있다.
public class Main {
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        String str = br.readLine();
        int len = str.length();
        sb.append(str.charAt(0));
        for(int i=1; i<len; i++){
            char curC = str.charAt(i);
            // sb의 마지막 문자와 현재 문자를 비교해서 현재 문자가 더 작으면 뒤에 붙이고
            // 현재 문자가 더 크면 앞에 붙인다.
            // 사전순으로 빠른 문자를 뒤에 몰아넣어야 이후에 마지막에 한 번 뒤집었을 때, 사전순으로 가장 빠르다.
            if(sb.charAt(sb.length()-1) < curC) sb.insert(0, curC);
            else sb.append(curC);
        }
        sb.reverse();
        System.out.println(sb);
    }
}