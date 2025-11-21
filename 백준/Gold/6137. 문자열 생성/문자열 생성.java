import java.io.*;

// 문자열 S에서 앞과 뒤를 보면서 둘 중에 사전순으로 더 빠른 문자를 선택해서 문자열 T에 추가한다.
// T에 추가할 문자는 뒤에만 추가할 수 있다.
// 같은 경우에는?
// cbazzbbc // cbbcbazz or cbacbbzz
public class Main {
    static String s, t;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++) sb.append(br.readLine());
        s = sb.toString();
        t = "";
        int left = 0; int right = s.length() - 1;
        sb = new StringBuilder();
        while(left <= right){
            int l = left; int r = right;
            boolean goLeft = false;
            // 내부를 탐색하면서 문자열 s의 앞, 뒤의 문자 중 어떤 것을 선택해야 할 지 결정
            while(l <= r){
                if(s.charAt(l) < s.charAt(r)){
                    goLeft = true;
                    break;
                }
                if(s.charAt(l) > s.charAt(r)) break;
                // left, right가 가리키는 문자가 같으면 내부로 이동
                else{ l++; r--; }
            }
            if(goLeft) sb.append(s.charAt(left++));
            else sb.append(s.charAt(right--));
        }
        for(int i=0; i<sb.length(); i++){
            System.out.print(sb.charAt(i));
            if((i+1) % 80 == 0) System.out.println();
        }
    }
}