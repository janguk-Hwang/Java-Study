import java.io.*;
import java.util.*;

// 어떤 문자를 정확히 K개를 포함하는 가장 짧은 연속 문자열의 길이를 구한다.
// 어떤 문자를 정확히 K개를 포함하고, 문자열의 첫 번째와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열의 길이를 구한다.
public class Main {
    static int t, k;
    static int[] alphabet;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        for(int i=0; i<t; i++){
            String str = br.readLine();
            k = Integer.parseInt(br.readLine());
            // k가 1이면 3, 4번 조건에 대해서 모두 1로 고정된다.
            if(k == 1) {
                sb.append("1 1").append("\n");
                continue;
            }
            alphabet = new int[26];
            for(int j=0; j<str.length(); j++) alphabet[str.charAt(j) - 'a']++;

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for(int j=0; j<str.length(); j++){
                // 문자열에서의 총 각 알파벳 개수가 k개보다 작으면 해당 알파벳으로는 3, 4번 구할 수 없음
                if(alphabet[str.charAt(j) - 'a'] < k) continue;
                int count = 1;
                for(int l=j+1; l<str.length(); l++){
                    if(str.charAt(j) == str.charAt(l)) count++;
                    if(count == k){
                        // 3, 4번 모두 첫 문자와 마지막 문자가 같을때를 계산해야 한다.
                        min = Math.min(min, l-j+1);
                        max = Math.max(max, l-j+1);
                        break;  // 없으면 다음 문자가 다른 문자이면 count가 여전히 k와 같아서 min, max가 갱신된다.
                    }
                }
            }
            if(min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) sb.append("-1").append("\n");
            else sb.append(min).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }
}