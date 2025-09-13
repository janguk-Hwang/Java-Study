import java.io.*;
import java.util.*;

// 사전순으로 가장 뒷서는 것을 출력하기 위해서 맨 앞자리(0)부터 s번째 인덱스까지에서 가장 큰 숫자를 찾는다.
// 이 숫자를 맨 앞자리로 이동시키고 이동한 칸 만큼 s를 감소시킨다.
// 그 다음 자리(1)도 위와 같은 방식으로 진행한다.
// 이렇게 s가 0이 될 때까지 혹은 변경한 자리수가 n-1이 될 때까지 반복한다.
// n은 최대 50이므로 시간초과 x
public class Main {
    static int n, s;
    static List<Integer> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) list.add(Integer.parseInt(st.nextToken()));
        s = Integer.parseInt(br.readLine());

        for(int i=0; i<n-1 && s>0; i++){
            int maxPos = i;
            int tempj = 0;
            for(int j=i+1; j<n && j-i<=s; j++){
                // s범위 내에 i번째 숫자보다 가장 큰 숫자를 찾음
                if(list.get(j) > list.get(maxPos)){
                    maxPos = j;
                    tempj = j;
                }
            }
            // maxPos가 i 그대로면 이동 x
            if(maxPos == i) continue;
            int temp = list.remove(maxPos);
            list.add(i, temp);
            s -= (maxPos - i);
        }
        for(int i=0; i<n; i++) sb.append(list.get(i)).append(" ");
        System.out.print(sb);
    }
}