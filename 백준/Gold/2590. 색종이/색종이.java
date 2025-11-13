import java.io.*;
import java.util.*;

// 6번 색종이의 개수만큼 판이 필요하다.
// 5번 색종이를 하나 붙이고 남는 부분은 1로 채운다. 1 - 11개
// 4번 색종이를 하나 붙이고 남는 부분은 2로 채우고 자리가 남으면 1로 마저 채운다. 2 - 5개, 1 - 10개
// 3번 색종이로 한 판에 최대 4개 붙인다. 마찬가지로 남는 부분은 2번 색종이로 또 남으면 1번 색종이로 채운다.
// 2번 색종이 ~~~
// 1번 색종이 ~~~
public class Main {
    // 3번 색종이를 채우고 남은 칸에 들어갈 수 있는 2번, 1번 색종이의 개수
    // 2번 색종이를 다 사용했다면 2번 색종이가 들어갈 수 있는 칸의 수 * 4만큼 1번 색종이를 넣을 수 있는 칸이 증가한다.
    static int[] twoBy3 = {0, 5, 3, 1};
    static int[] oneBy3 = {0, 7, 6, 5};
    static int rst;
    static int[] cnt = new int[7];
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        for(int i=1; i<=6; i++) cnt[i] = Integer.parseInt(br.readLine());
        rst = 0;
        rst += cnt[6];
        rst += cnt[5];
        cnt[1] = Math.max(0, cnt[1] - 11 * cnt[5]);
        rst += cnt[4];
        // 사용할 수 있는 2번 색종이의 수
        int useTwo = 5 * cnt[4];
        if(cnt[2] >= useTwo) cnt[2] -= useTwo;
        else{
            // 2로 빈 곳을 채우고 남은 칸
            int temp = useTwo - cnt[2];
            cnt[1] = Math.max(0, cnt[1] - 4 * temp);
            cnt[2] = 0;
        }

        rst += cnt[3] / 4;
        int remainBy3 = cnt[3] % 4;
        if(remainBy3 > 0){
            int need2 = twoBy3[remainBy3];
            int need1 = oneBy3[remainBy3];
            // 남은 2번 색종이의 수보다 2번 색종이가 들어갈 수 있는 칸의 수가 작으면
            if(need2 <= cnt[2]) cnt[2] -= need2;
            // 반대의 경우 -> 남은 자리를 1로 채운다.
            else{
                int remainBy3By2 = need2 - cnt[2];
                cnt[1] = Math.max(0, cnt[1] - 4 * remainBy3By2);
                cnt[2] = 0;
            }
            cnt[1] = Math.max(0, cnt[1] - need1);
            rst++;
        }

        if(cnt[2] > 9) rst += cnt[2] / 9;
        int remain = cnt[2] % 9;
        if(remain > 0){
            rst++;
            // 남은 자리 1로 채우기
            cnt[1] = Math.max(0, cnt[1] - (6 * 6 - remain * 4));
        }

        rst += cnt[1] / 36;
        if(cnt[1] % 36 > 0) rst++;
        System.out.print(rst);
    }
}