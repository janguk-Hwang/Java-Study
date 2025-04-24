import java.io.*;
import java.util.*;

// 2*10^9 * 10^6 = 2*10^15은 long으로 커버 가능
public class Main {
    static int n, x, y;
    static ArrayList<int[]> list;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            list.add(new int[]{x, y});
        }

        // x좌표를 기준으로 정렬
        list.sort((a, b) -> a[0] - b[0]);

        long total = 0;
        int curx = list.get(0)[0];
        int cury = list.get(0)[1];
        for(int i=0; i<n; i++){
            int x = list.get(i)[0];
            int y = list.get(i)[1];

            // 겹치는 경우
            if(x <= cury) cury = Math.max(cury, y);
            // 안 겹치는 경우
            else{
                total += cury - curx;
                curx = x;
                cury = y;
            }
        }
        // 마지막 점이 겹쳐지는 경우, 안 겹쳐지는 경우 둘 다 위 코드에서 마지막 추가 안됨.
        // 위 코드는 안 겹치는 경우 현재꺼만 누적하기 때문
        total += cury - curx;
        System.out.println(total);
    }
}