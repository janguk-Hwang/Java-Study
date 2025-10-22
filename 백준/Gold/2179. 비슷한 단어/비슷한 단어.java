import java.io.*;
import java.util.*;

// 맵에 <단어, 인덱스> 형태로 저장해놓는다.
// 입력받은 단어들을 정렬하고 인접한 단어끼리 공통 접두사의 길이를 알아내 최대 공통 접두사의 길이를 저장한다.
// 최대 공통 접두사를 갖는 접두사를 찾았을 때,
// 우선 S가 입력되는 순서대로 제일 앞쪽에 있는 단어인 경우를 출력하고,
// 그런 경우도 여러 개 있을 때에는 그 중에서 T가 입력되는 순서대로 제일 앞쪽에 있는 단어인 경우를 출력한다.
public class Main {
    static List<String> list = new ArrayList<>();
    static int n, max;
    static String maxS = "", maxT = "";
    static Map<String, Integer> map = new HashMap<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            String word = br.readLine();
            list.add(word);
            map.put(word, i);
        }
        max = -1;
        for(int i=0; i<n-1; i++){
            String s1 = list.get(i);
            for(int j=i+1; j<n; j++){
                String s2 = list.get(j);
                int len = lch(s1, s2);
                if(len > max){
                    max = len;
                    maxS = i < j ? s1 : s2;
                    maxT = i < j ? s2 : s1;
                    continue;
                }
                if(len == max){
                    int cs = i;
                    int ct = j;
                    int ps = map.get(maxS);
                    int pt = map.get(maxT);
                    if(cs < ps || (cs == ps && ct < pt)){
                        maxS = i < j ? s1 : s2;
                        maxT = i < j ? s2 : s1;
                    }
                }
            }
        }
        System.out.println(maxS + "\n" + maxT);
    }

    // longest common header(최대 공통 접두사)의 길이 반환
    static int lch(String s1, String s2){
        int cnt = 0;
        int len = Math.min(s1.length(), s2.length());
        for(int i=0; i<len; i++){
            if(s1.charAt(i) != s2.charAt(i)) return cnt;
            if(s1.charAt(i) == s2.charAt(i)) cnt++;
        }
        return cnt;
    }
}