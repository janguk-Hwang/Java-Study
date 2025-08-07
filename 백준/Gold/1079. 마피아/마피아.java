import java.io.*;
import java.util.*;

// 밤에만 날이 넘어간다.
public class Main {
    static boolean[] alive, isMafia;
    static int n, E, max;
    static int[] guilt;
    static int[][] R;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        guilt = new int[n];
        R = new int[n][n];
        alive = new boolean[n];
        Arrays.fill(alive, true);
        isMafia = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) guilt[i] = Integer.parseInt(st.nextToken());
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) R[i][j] = Integer.parseInt(st.nextToken());
        }
        E = Integer.parseInt(br.readLine());    // 은진의 참가자 번호
        isMafia[E] = true;
        max = 0;
        backTracking(0);
        System.out.print(max);
    }
    // 재귀 호출하면서 날짜, 유죄 지수, 생존 여부가 변경 됨
    public static void backTracking(int day){
        // 종료 조건(은진이 죽으면 종료)
        if(!alive[E]){
            max = Math.max(max, day);
            return;
        }

        int aliveCnt = 0;
        int mafiaCnt = 0;
        int citizenCnt = 0;
        // 살아있는 사람 수, 마피아, 시민 수 세기
        for(int i=0; i<n; i++){
            if(alive[i]){
                aliveCnt++;
                if(isMafia[i]){
                    mafiaCnt++;
                    continue;
                }
                citizenCnt++;
            }
        }

        // 마피아나 시민이 전멸하면 게임 종료
        if(mafiaCnt == 0 || citizenCnt == 0){
            max = Math.max(max, day);
            return;
        }

        // 모든 후보 선택지
        // 생존자 수가 짝수인 경우(밤, 마피아가 죽임)
        if(aliveCnt % 2 == 0){
            for(int i=0; i<n; i++){
                // 가지치기
                // 이미 죽은 사람이나 은진이 본인은 제외
                if(!alive[i] || i == E) continue;
                // 상태 저장 또는 선택
                alive[i] = false;
                for(int j=0; j<n; j++) if(alive[j]) guilt[j] += R[i][j];
                backTracking(day + 1);
                for(int j=0; j<n; j++) if(alive[j]) guilt[j] -= R[i][j];
                alive[i] = true;
            }
        }

        // 낮, 유죄 지수가 높은 사람을 죽임(지수가 같으면 작은 번호부터)
        if(aliveCnt % 2 == 1){
            int maxGuilt = Integer.MIN_VALUE;
            int killIdx = -1;
            for(int i=0; i<n; i++){
                if(alive[i]){
                    if(guilt[i] > maxGuilt){
                        maxGuilt = guilt[i];
                        killIdx = i;
                    }
                }
            }

            alive[killIdx] = false;
            backTracking(day);
            alive[killIdx] = true;
        }
    }
}