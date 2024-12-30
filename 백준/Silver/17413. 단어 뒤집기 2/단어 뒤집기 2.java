import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //단어끼리 공백으로 분리되어 있으므로 분리,
        Stack<Character> stack = new Stack<>();
        boolean flag = true;
        String s = br.readLine();

        for(int i=0; i<s.length(); i++){
            //<을 만났을 때 <출력
            if(s.charAt(i)== '<'){
                while(!stack.isEmpty()){
                    System.out.print(stack.pop());
                }
                flag = false;
                System.out.print(s.charAt(i));
            }
            //>을 만났을 때 >출력
            else if(s.charAt(i)=='>'){
                flag = true;
                System.out.print(s.charAt(i));
            }
            //태그 내용 출력
            else if(!flag){
                System.out.print(s.charAt(i));
            }

            //태그가 아닌 경우
            else {
                if (s.charAt(i) == ' ') { //공백을 만나면
                    while(!stack.isEmpty()){
                        System.out.print(stack.pop()); //stack에 담긴 단어 출력
                    }
                    System.out.print(s.charAt(i)); //현재 문자 출력
                }
                else {
                    stack.push(s.charAt(i)); //아무것도 아닌 경우 push
                }
            }
        }
        while(!stack.isEmpty()){
            System.out.print(stack.peek());
            stack.pop();
        }
    }
}