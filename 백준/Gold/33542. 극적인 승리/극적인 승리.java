import java.io.*;
import java.util.*;

public class Main {
    static int n, a, b;
    static int[] l, r;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken()); // 동현의 점수
        b = Integer.parseInt(st.nextToken()); // 주원의 현재 점수
        n = Integer.parseInt(br.readLine());
        l = new int[n];
        r = new int[n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            l[i] = Integer.parseInt(st.nextToken());
            r[i] = Integer.parseInt(st.nextToken());
        }
        long minTotalScore = Long.MAX_VALUE;        // 이기기 위한 최소 총 점수
        int bestL = -1;     // 최적의 왼손 과녁 번호
        int bestR = -1;     // 최적의 오른손 과녁 번호
        // 1. 왼손만 맞춘 경우
        for(int i=0; i<n; i++){
            long total = b + l[i];
            // 동현이를 이기고 기존 최소 총 점수보다 작은 경우
            if(total > a && total < minTotalScore){
                minTotalScore = total;
                bestL = i + 1;
                bestR = -1;
            }
        }
        // 2. 오른손만 맞춘 경우
        for(int i=0; i<n; i++){
            long total = b + r[i];
            if(total > a && total < minTotalScore){
                minTotalScore = total;
                bestL = -1;
                bestR = i + 1;
            }
        }
        // 3. 양손 다 안 맞추는 경우(이미 이긴 상황, 한 손으로 맞춘 최적의 점수보다 기존 점수가 낮은 경우)
        if(b > a && b < minTotalScore){
            minTotalScore = b;
            bestL = -1;
            bestR = -1;
        }
        // 4. 양손 던지는 경우 (i != j) -> 고려해야 하는 조합이 n^2이므로 완전탐색은 시간초과 발생
        int[][] sortedR = new int[n][2];        // 점수, 인덱스
        for(int i=0; i<n; i++){
            sortedR[i][0] = r[i];
            sortedR[i][1] = i;
        }
        // 오른손으로 맞췄을 때의 점수기준으로 정렬
        Arrays.sort(sortedR, (a, b) -> a[0] - b[0]);
        for(int i=0; i<n; i++){
            int need = a - b - l[i] + 1;    // 오른손으로 얻어야 하는 점수의 최소컷
            if(need <= 0) need = 0;
            // 결정 문제: mid번째 점수로 이길 수 있는가?
            int start = -1; int end = n-1;
            while(start + 1 < end){
                int mid = (start + end) / 2;
                // 더 작은 점수가 될 수 있으므로 좌측 탐색
                if(sortedR[mid][0] >= need) end = mid;
                else start = mid;
            }
            // end가 초기값 n 그대로이면 한 번도 need와 같거나 큰 오른손 점수가 없었다는 것을 의미
            if(end == n-1) continue;

            int score = sortedR[end][0];
            int idx = sortedR[end][1];
            // 왼쪽 과녁과 오른쪽 과녁이 같으면 다음으로 점수가 큰 오른손 과녁으로
            if(idx == i){
                score = sortedR[end + 1][0];
                idx = sortedR[end + 1][1];
            }
            long total = b + l[i] + score;
            if(total > a && total < minTotalScore){
                minTotalScore = total;
                bestL = i + 1;
                bestR = idx + 1;
            }
        }
        // 어떻게 해도 이길 수 없는 경우
        if(minTotalScore == Long.MAX_VALUE){
            System.out.print("No");
            return;
        }
        System.out.print(bestL + " " + bestR);
    }
}