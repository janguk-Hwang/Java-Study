import java.io.*;
import java.util.*;

// 레벨-L 버거는 B + (L-1) + p + (L-1) + B 형태의 점화식을 가진다.
// 구해야 하는 것은 x장 먹었을 때, 먹은 "패티"의 개수
// 레벨-0 버거는 패티 한 장 자체
// 전체 버거의 장수는 length(L-1) * 2 + 3
// 전체 패티의 개수는 patty(L) * 2 + 1
public class Main {
    static int n;
    static long x;
    static long[] length, patty;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        x = Long.parseLong(st.nextToken());
        length = new long[n+1];
        patty = new long[n+1];
        length[0] = 1;
        patty[0] = 1;
        for(int i=1; i<=n; i++){
            length[i] = 2 * length[i-1] + 3;
            patty[i] = 2 * patty[i-1] + 1;
        }
        System.out.print(func(n, x));
    }

    // 햄버거는 왼쪽 버거 / 가운데 패티 1장 / 오른쪽 버거로 나뉜다.
    public static long func(int n, long x){
        // 0 레벨 버거
        if(n == 0) return 1;
        // 0장 먹은 경우
        if(x == 1) return 0;
        // x와 length[n]이 같은 경우(버거를 남기지 않고 다 먹은 경우)
        if(x == length[n]) return patty[n];
        // 아랫부분 버거의 일부 또는 전부만 먹은 경우
        if(x < length[n-1] + 2) return func(n-1, x-1);
        // 정확히 아랫부분 버거 + 가운데 패티까지 먹은 경우
        if(x == length[n-1] + 2) return patty[n-1] + 1;
        //
        if(x > length[n-1] + 2){
            // 왼쪽 버거에서의 패티 개수 + 가운데 패티 1개 +
            return patty[n-1] + 1 + func(n-1, x - (length[n-1] + 2));
        }
        return 0;
    }
}