import java.io.*;
import java.util.*;

// 피연산자, 괄호(여는, 닫는), 연산자
public class Main {
    static Stack<Character> stack = new Stack<>();
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String s = br.readLine();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            // 피연산자
            if(c >= 'A' && c <= 'Z') sb.append(c);
            // 여는 괄호 - 괄호 안의 연산자들이 먼저 계산되어야 한다. -> 새로운 우선순위 영역의 시작
            else if(c == '(') stack.push(c);
            // 닫는 괄호 - 여는 괄호가 나올 때까지 스택에서 pop
            else if(c == ')'){
                while(!stack.isEmpty() && stack.peek() != '(') sb.append(stack.pop());
                // 여는 괄호는 결과에 나오지 않으므로 pop
                if(!stack.isEmpty() && stack.peek() == '(') stack.pop();
            }
            // 연산자
            else{
                // 스택 안의 연산자가 현재 연산자보다 우선순위가 높거나 같으면 꺼냄 - 현재 연산자보다 높거나 같으면 먼저 나온 연산자의 순서가 결정됨
                while(!stack.isEmpty() && priority(stack.peek()) >= priority(c)){
                    sb.append(stack.pop());
                }
                stack.push(c);
            }
        }
        while(!stack.isEmpty()) sb.append(stack.pop());
        System.out.print(sb);
    }

    static int priority(char c){
        if(c == '*' || c == '/') return 2;
        else if(c == '+' || c == '-') return 1;
        else return 0;
    }
}