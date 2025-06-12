import java.util.*;
import java.io.*;

public class Main {
    static int t;
    static HashMap<Character, Integer> map = new HashMap<>();
    static int[] value = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    static String[] roma = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            String temp = br.readLine();
            if(Character.isDigit(temp.charAt(0))){
                araToRoma(Integer.parseInt(temp));
                continue;
            }
            romaToAra(temp);
        }
        System.out.print(sb);
    }

    public static void araToRoma(int number){
        for(int i=0; i<value.length; i++){
            while(number >= value[i]){
                number -= value[i];
                sb.append(roma[i]);
            }
        }
        sb.append("\n");
    }

    public static void romaToAra(String str){
        int sum = 0;
        int pre = 1001;
        for(int i=0; i<=str.length()-1; i++){
            int now = map.get(str.charAt(i));
            if(pre < now){
                sum += now - 2 * pre;
                pre = now;
                continue;
            }
            sum += now;
            pre = now;
        }
        sb.append(sum).append("\n");
    }
}