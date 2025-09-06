import java.io.*;
import java.util.*;

// 결과에서 역으로 진행하면서 총 15게임을 진행했을 때 모든 팀이 0, 0, 0으로 되어있으면 가능한 결과인 것이다.
// 백트래킹으로 진행
public class Main {
    static int[] win = new int[6];
    static int[] draw = new int[6];
    static int[] lose = new int[6];
    static int[][] comb = new int[15][2];
    static boolean flag;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int idx = 0;
        // 15가지 경기 방법을 comb에 저장(0, 1 | 0, 2 | 0, 3 | ... 4, 5)
        for(int i=0; i<6; i++){
            for(int j=i+1; j<6; j++){
                comb[idx++] = new int[]{i, j};
            }
        }
        for(int i=0; i<4; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<6; j++){
                win[j] = Integer.parseInt(st.nextToken());
                draw[j] = Integer.parseInt(st.nextToken());
                lose[j] = Integer.parseInt(st.nextToken());
            }
            flag = false;
            backtracking(15);
            sb.append(flag ? 1 : 0).append(" ");
        }
        System.out.print(sb);
    }

    public static void backtracking(int depth){
        if(flag) return;
        // 종료 조건(15경기를 진행하고 모든 팀이 0, 0, 0이면 가능한 결과였음을 알 수 있음)
        if(depth == 0){
            for(int i=0; i<6; i++){
                if(win[i] != 0 || draw[i] != 0 || lose[i] != 0) return;
            }
            flag = true;
            return;
        }
        // 가능한 모든 선택, 가지치기(불가능하면 진행x)
        // 15가지 경우에 대해 승패, 패승, 무승부를 각각 진행
        int a = comb[depth-1][0];
        int b = comb[depth-1][1];
        // i승, j패
        if(win[a] > 0 && lose[b] > 0){
            win[a]--; lose[b]--;
            backtracking(depth-1);
            win[a]++; lose[b]++;
        }
        // i패, j승
        if(lose[a] > 0 && win[b] > 0){
            lose[a]--; win[b]--;
            backtracking(depth-1);
            lose[a]++; win[b]++;
        }
        // 무승부
        if(draw[a] > 0 && draw[b] > 0){
            draw[a]--; draw[b]--;
            backtracking(depth-1);
            draw[a]++; draw[b]++;
        }
    }
}