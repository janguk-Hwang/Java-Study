import java.io.*;
import java.util.*;

// 어떤 초밥을 먹었는지 저장하는 배열은 boolean으로 하면 안 됨.
// 어떤 종류의 초밥을 2개 이상먹은 경우도 있기 때문
public class Main {
    static int n, d, k, c;
    static int[] sushi, eatCnt;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        sushi = new int[n];
        for(int i=0; i<n; i++) sushi[i] = Integer.parseInt(br.readLine());
        eatCnt = new int[d + 1];    // 각 종류의 초밥을 먹을 횟수, 초밥 종류 번호는 1 ~ d
        int eatTypeCnt = 0;     // 현재 먹은 초밥 종류 수
        for(int i=0; i<k; i++){
            if(eatCnt[sushi[i]] == 0) eatTypeCnt++;
            eatCnt[sushi[i]]++;
        }
        int max = eatTypeCnt;
        // 먹은 k개 초밥에 c번 초밥을 먹지 않은 경우
        if(eatCnt[c] == 0) max = eatTypeCnt + 1;
        // 초기 시작 위치에서 한 칸씩 한 바퀴 회전
        for(int i=1; i<n; i++){
            // 한 칸씩 회전(이전 턴의 시작점 초밥 제거)
            int removeSushi = sushi[i - 1];
            eatCnt[removeSushi]--;
            if(eatCnt[removeSushi] == 0) eatTypeCnt--;
            // 한 칸 회전으로 인해 추가 되는 초밥에 대한 처리
            int addSushi = sushi[(i + k - 1) % n];
            eatCnt[addSushi]++;
            if(eatCnt[addSushi] == 1) eatTypeCnt++;
            // 먹은 k개 초밥의 종류에서 쿠폰 초밥의 종류가 없으면 1만큼 증가한 값으로 갱신, 아니면 그대로 비교하여 갱신
            max = Math.max(max, eatTypeCnt + ((eatCnt[c] == 0) ? 1 : 0));
        }
        System.out.print(max);
    }
}