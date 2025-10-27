import java.io.*;
import java.util.*;

public class Main {
    static int t;
    static int n;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            n = Integer.parseInt(br.readLine());
            backtracking(1, "1");
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void backtracking(int depth, String str){
        if(depth == n){
            if(calc(str) == 0) sb.append(str).append("\n");
            return;
        }
        backtracking(depth + 1, str + " " + (depth + 1));
        backtracking(depth + 1, str + "+" + (depth + 1));
        backtracking(depth + 1, str + "-" + (depth + 1));
    }

    static int calc(String str){
        String replaced = str.replace(" ", "");
        List<Character> op = new ArrayList<>();
        List<Integer> num = new ArrayList<>();
        int number = 0;
        for(int i=0; i<replaced.length(); i++){
            char c = replaced.charAt(i);
            if(c == '+' || c == '-'){
                num.add(number);
                op.add(c);
                number = 0;
            }
            else number = number * 10 + (c - '0');
        }
        num.add(number);
        int result = num.get(0);
        for(int i=0; i<op.size(); i++){
            if(op.get(i) == '+') result += num.get(i + 1);
            else result -= num.get(i + 1);
        }
        return result;
    }
}