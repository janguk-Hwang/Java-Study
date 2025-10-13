import java.io.*;
import java.util.*;

public class Main {
    static int[][] rule;
    static int n, k, d;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        rule = new int[k][3];
        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            rule[i][0] = Integer.parseInt(st.nextToken());  // 규칙 시작점
            rule[i][1] = Integer.parseInt(st.nextToken());  // 규칙 끝점
            rule[i][2] = Integer.parseInt(st.nextToken());  // 규칙 간격
        }
        int start = 0; int end = n;
        // 결정 문제: mid번째 상자까지 규칙대로 도토리를 넣었을 때, 사용된 도토리의 수가 d보다 크거나 같은가?
        // false: mid를 오른쪽으로 이동시켜서 mid번째 상자까지 사용된 도토리의 수를 늘려야 한다.
        // true: mid를 왼쪽으로 이동시켜 사용된 도토리의 수를 줄여야 한다.
        // mid번째 상자에 d번째 도토리가 위치하도록
        // fffffttttt
        while(start + 1 < end){
            int mid = (start + end) / 2;
            long used = 0;
            for(int i=0; i<k; i++){
                int[] temp = rule[i];
                int ruleS = temp[0];
                int ruleE = temp[1];
                int ruleT = temp[2];
                int last = Math.min(mid, ruleE);
                if(last >= ruleS) used += (last - ruleS) / ruleT + 1;
            }
            if(used < d) start = mid;
            else end = mid;
        }
        System.out.print(end);
    }
}