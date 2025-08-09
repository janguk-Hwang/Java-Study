import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 10001;
    static boolean[] visited = new boolean[MAX];        // true: 생성자가 있다, false: 생성자가 없어 셀프 넘버이다
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        for(int i=1; i<MAX; i++){
            String str = String.valueOf(i);
            int temp = i;
            for(int j=0; j<str.length(); j++) temp += str.charAt(j) - '0';
            if(temp < MAX) visited[temp] = true;
        }
        for(int i=1; i<MAX; i++) if(!visited[i]) sb.append(i).append("\n");
        System.out.print(sb);
    }
}