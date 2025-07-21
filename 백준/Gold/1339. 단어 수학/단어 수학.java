import java.io.*;
import java.util.*;

// 같은 알파벳은 같은 숫자로 바꿔야 하며, 두 개 이상의 알파벳이 같은 숫자로 바뀌어지면 안 된다.
// 중요한 것은 문자가 몇의 자리인지이다. 더 큰 자리부터 9 ~ 1까지로 정한다.
public class Main {
    static int n;
    static Map<Character, Integer> map = new HashMap<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            String temp = br.readLine();
            int len = temp.length();
            for(int j=0; j<len; j++){
                char c = temp.charAt(j);
                int weight = (int)Math.pow(10, len - j - 1);
                // 핵심 부분: 기존 가중치에 누적한다. 덮어쓰지 않고 누적해야 실제 해당 문자가 가지는 가중치를 계산할 수 있기 때문
                // 더 많이 더 높은 가중치로 출현한 문자의 우선순위가 더 높아야 하기 때문
                map.put(c, map.getOrDefault(c, 0) + weight);
            }
        }
        List<Integer> list = new ArrayList<>(map.values());
        list.sort(Collections.reverseOrder());      // 가중치가 높은 문자순으로 정렬
        int num = 9;
        int result = 0;
        for(Integer temp : list){
            result += num * temp;
            num--;
        }
        System.out.print(result);
    }
}