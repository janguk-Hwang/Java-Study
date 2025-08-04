import java.io.*;
import java.util.*;

public class Main {
    static int n, k, max = 0;
    static boolean[] visited;
    static char[][] words;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        if(k < 5){
            System.out.println(0);
            return;
        }
        if(k == 26){
            System.out.println(n);
            return;
        }
        visited = new boolean[26];
        visited['a' - 'a'] = true;
        visited['c' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;

        words = new char[n][];
        for(int i=0; i<n; i++){
            String str = br.readLine().replace("anta", "").replace("tica", "");
            Set<Character> set = new HashSet<>();
            for(char c : str.toCharArray()){
                if(!visited[c - 'a']){
                    set.add(c);
                }
            }
            words[i] = new char[set.size()];
            int idx = 0;
            for(char c : set){
                words[i][idx++] = c;
            }
        }

        backtrack(0, 0);
        System.out.println(max);
    }

    static void backtrack(int idx, int depth){
        if(depth == k - 5){
            int count = 0;
            for(char[] word : words){
                boolean flag = true;
                for(char c : word){
                    if(!visited[c - 'a']){
                        flag = false;
                        break;
                    }
                }
                if(flag) count++;
            }
            max = Math.max(max, count);
            return;
        }

        for(int i=idx; i<26; i++){      // i를 0부터 25까지 반복하면 같은 알파벳을 여러 번 배우게되므로 중복 조합을 막기 위해(b, d는 되지만 d, b는 없음)
            if(!visited[i]){
                visited[i] = true;
                backtrack(i + 1, depth + 1);
                visited[i] = false;
            }
        }
    }
}
