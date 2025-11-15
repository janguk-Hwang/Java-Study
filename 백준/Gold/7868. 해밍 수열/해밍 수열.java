import java.io.*;
import java.util.*;

// 소인수가 p1, p2, p3로만 이루어진 자연수의 오름차순 목록이 해밍 수열
// p1, p2, p3를 모두 소인수로 가질 필요는 없다.
// 해밍 수열은 세 소수로 만들어지는 수로만 이루어져있기 때문에 세 소수로 계속해서 곱해서 해밍 수열에 추가해 나가면 된다.
// 7 13 19  --- 1
// 7 13 19 49
// 7 13 19 49 91
// 7 13 19 49 91 133
// 7 13 19 49 91 133 169
// 7 13 19 49 91 133 169 247
// 7 13 19 49 91 133 169 247 343
// 7 13 19 49 91 133 169 247 343 361
// 7 13 19 49 91 133 169 247 343 361 ...
// 현재 가장 작은 수를 현재 해밍 수열에 존재하는 수들과 모두 곱한다.
// 현재 가장 작은 수는 필요없으므로 제거
// i번 반복했을 때 현재 가장 작은 수가 i번째 수이다.
public class Main {
    static long p1, p2, p3, i;
    static TreeSet<Long> set = new TreeSet<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        p1 = Long.parseLong(st.nextToken());
        p2 = Long.parseLong(st.nextToken());
        p3 = Long.parseLong(st.nextToken());
        i = Long.parseLong(st.nextToken());
        set.add(p1); set.add(p2); set.add(p3);
        long rst = 0;
        for(int j=0; j<i; j++){
            rst = set.pollFirst();
            set.add(rst * p1);
            set.add(rst * p2);
            set.add(rst * p3);
        }
        System.out.print(rst);
    }
}