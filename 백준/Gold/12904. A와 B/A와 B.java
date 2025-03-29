import java.util.*;
import java.io.*;

// 문자열의 뒤에 A를 추가한다.
// 문자열을 뒤집고 뒤에 B를 추가한다.
// 1 ≤ S의 길이 ≤ 999, 2 ≤ T의 길이 ≤ 1000, S의 길이 < T의 길이
public class Main {
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder s = new StringBuilder(br.readLine());
        StringBuilder t = new StringBuilder(br.readLine());

        while(s.length() < t.length()){
            if(t.charAt(t.length() - 1) == 'A'){
                t.deleteCharAt(t.length() - 1);
            }
            else if(t.charAt(t.length() - 1) == 'B'){
                t.deleteCharAt(t.length() - 1);
                t.reverse();
            }
        }

        if(t.toString().equals(s.toString())){
            System.out.println("1");
        }
        else System.out.println("0");
    }
}
