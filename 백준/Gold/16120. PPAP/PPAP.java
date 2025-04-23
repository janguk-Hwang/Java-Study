import java.io.*;
import java.util.*;

public class Main {
    static Stack<Character> stack = new Stack<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        // 스택에 문자를 하나씩 넣으면서 A를 만나면 stack[i+1]이 p이고, stack의 사이즈가 2 이상인지 확인(앞에 p가 2개 있는지 확인)
        // 즉, 문자열의 문자를 하나씩 스택에 넣어보면서 PPAP가 완성이 되면 바로 P로 만듦
        // 만약 A를 만났는데 앞에 PP가 없고 뒤에 P도 없어 A가 그냥 앞에 남게되면 그 문자열은 앞으로도 PPAP 문자열이 될 수 없다.
        String str = br.readLine();
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            if(c == 'A'){
                if(stack.size() >= 2 && i+1 < str.length() && stack.get(stack.size() - 1) == 'P' && stack.get(stack.size() - 2) == 'P'){
                    if(str.charAt(i+1) == 'P'){
                        stack.pop();    // A 앞 P pop()
                        stack.pop();    // A 앞앞 P pop()
                        i++;
                        stack.push('P');
                    }
                    // PPAAP
                    else {
                        System.out.println("NP");
                        return;
                    }
                }
                // A를 만났는데 PPAP가 되지 않은 경우
                else {
                    System.out.println("NP");
                    return;
                }
            }
            else stack.push(c);
        }
        if(stack.size() == 1 && stack.pop() == 'P') System.out.println("PPAP");
        else System.out.println("NP");
    }
}