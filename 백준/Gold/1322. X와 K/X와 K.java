import java.io.*;
import java.util.*;

// 더한 값이 or 연산 결과와 같으려면 x의 비트가 0인 곳에 1을 넣는 것을 늘려나가야 한다.
// k = 101 -> 10 -> 1
// 101 10 = 101
// 1개
// 101 1000 = 1110
// 101 1010 = 1110
// 2개
// 101 10000 = 10101
// 101 10010 = 10111
// 101 11000 = 11101
// 101 11010 = 11111
// 4개
// 101 100000 = 100101
// 101 100010 = 100111
// 101 101000 = 101101
// 101 101010 = 101111
// 101 110000 = 110101
// 101 110010 = 110111
// 101 111000 = 111101
// 101 111010 = 111111
// 8개
public class Main {
    static long x, k;
    static List<Integer> list = new ArrayList<>();      // 많아봤자 63~4개 저장
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        x = Long.parseLong(st.nextToken());
        k = Long.parseLong(st.nextToken());
        for(int i=0; i<62; i++) if((x & (1L << i)) == 0) list.add(i);
        long rst = 0;
        for(int i=list.size()-1; i>=0; i--){
            if(k == 0) break;
            long temp = 1L << i;
            if(k >= temp){
                rst += 1L << list.get(i);
                k -= temp;
            }
        }
        System.out.print(rst);
    }
}