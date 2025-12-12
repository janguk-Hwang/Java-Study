import java.io.*;
import java.util.*;

// A B, B C
public class Main {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String s = br.readLine();
        Deque<Integer> A = new ArrayDeque<>();
        Deque<Integer> B = new ArrayDeque<>();
        int rst = 0;
        // BC 제거
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c == 'B') B.addLast(i);
            else if(c == 'C'){
                if(!B.isEmpty()){
                    // 가장 앞의 B와 함께 제거
                    B.pollFirst();
                    rst++;
                }
            }
        }

        // AB 제거
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c == 'A') A.addLast(i);
            else if(c == 'B'){
                if(!B.isEmpty() && B.peekFirst() == i){
                    if(!A.isEmpty()){
                        A.pollFirst();
                        B.pollFirst();
                        rst++;
                    }
                    else B.pollFirst();
                }
            }
        }
        System.out.println(rst);
    }
}
// ABCBBACBABB
// ABBACBABB
// ABABABB
// BBBBABABABB
// AAAAAAABABABB
// BBBBBBAAAAA