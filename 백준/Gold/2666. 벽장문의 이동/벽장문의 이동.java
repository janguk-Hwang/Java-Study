import java.io.*;
import java.util.*;

public class Main {
    static int n, m, result;
    static int[] arr;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int one = Integer.parseInt(st.nextToken());
        int two = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());
        arr = new int[m];
        for(int i=0; i<m; i++) arr[i] = Integer.parseInt(br.readLine());
        result = Integer.MAX_VALUE;
        func(0, 0, one, two);
        System.out.print(result);
    }

    // 사용할 벽장의 번호, 이동횟수, 열려있는 벽장 번호1, 2
    public static void func(int depth, int cnt, int closet1, int closet2){
        if(cnt >= result) return;
        if(depth == m){
            result = cnt;
            return;
        }
        // closet1 선택
        // 다음 깊이, 지금까지의 이동 횟수, 선택한 벽장 번호, 선택되지 않은 벽장 번호 그대로
        func(depth + 1, cnt + Math.abs(closet1 - arr[depth]), arr[depth], closet2);
        // closet2 선택
        func(depth + 1, cnt + Math.abs(closet2 - arr[depth]), closet1, arr[depth]);
    }
}