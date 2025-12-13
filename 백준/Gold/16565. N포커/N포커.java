import java.io.*;
import java.util.*;

// n개의 카드를 뽑았을 때, 포카드를 만들 수 있는 경우의 수
// 포카드는 하나만 있어도 승리
// 한 번에 여러 포카드가 있을 수 있다.
// 확률이 아니라 경우의 수를 출력해야 한다.
public class Main {
    static int n, rst;
    static final int MOD = 10_007;
    static int[][] comb = new int[53][53];  // 1C1부터 53C53까지의 수를 담을 배열(MOD로 나눈 나머지)
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        rst = 0;
        for(int i=0; i<=52; i++) comb[i][0] = 1;
        for(int i=1; i<=52; i++){
            for(int j=1; j<=52; j++){
                // 파스칼의 삼각형
                comb[i][j] = (comb[i - 1][j] + comb[i - 1][j - 1]) % MOD;
            }
        }
        // i: 포카드의 개수, 포카드의 수*4가 뽑은 카드의 수보다 많을 수는 없음
        for(int i=1; i<=13 && n-4*i>=0; i++){
            // n이 10이고, 포카드가 1개면 comb[48][6]: 남은 48개의 카드에서 6개를 뽑는 경우의 수
            // 13개의 숫자 중에 포카드인 숫자 i개를 뽑는 경우의 수
            // 그런데 남은 카드에서 6개를 뽑는 경우에서 포카드가 발생할 수 있다.
            int duplication = (comb[52 - 4 * i][n - 4 * i] * comb[13][i]) % MOD;
            if(i%2 == 1) rst = (rst + duplication) % MOD;
            else rst = (rst - duplication + MOD) % MOD;
        }
        System.out.println(rst);
    }
}
// 예를 들어 i=4이고, n은 26일 때, 36C10 * 13C4
// 1478이 포 카드라면 36C10에 남은 숫자 23569 중에 10개를 뽑아 포카드를 만드는 경우가 포함될 수 있다.
// 물론 13C4를 곱하기 때문에 1234, 1589, 4567 등 가능한 모든 경우들이 포카드로 선택될 수 있다.
// i=4라는 것은 일단 포카드가 4개가 있다는 것을 의미하고 추가로 포카드가 더 발생할 수 있다는 것이다.
// 48C16 * 13C1 - 44C12 * 13C2 + 40C8 * 13C3 - 36C4 * 13C4 + 32C0 * 13C5

// 3 5 6 7 8
// i=1일 때, 13C1에서 3이 될 수도 5가 될 수도 6이 될 수도 7이 될 수도 8이 될 수도 있어서 3 5 6 7 8이 5번 세진다
// i=2일 때, 13C2에서 35, 36, 37, 38, 56, 57, 58, 67, 68, 78이 될 수 있다. 3 5 6 7 8이 10번 세진다.
// i=3일 때, 13C3에서 356, 357, 358, 367, 368, 378, 567, 568, 578, 678이 될 수 있다. 3 5 6 7 8이 10번 세진다.
// i=4일 때, 13C4에서 3567, 3568, 3578, 3678, 5678이 될 수 있다. 3 5 6 7 8이 5번 세진다.
// i=5일 때, 13C5에서 35678이 한 번 세진다.