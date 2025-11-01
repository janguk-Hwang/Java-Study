import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int aCnt = n / 2;
        int bCnt = n - aCnt;
        int maxPossible = aCnt * bCnt;
        if(k > maxPossible){
            System.out.println(-1);
            return;
        }
        int remainA = aCnt; // 남은 'A' 개수
        int remainB = bCnt; // 남은 'B' 개수
        int remainK = k;  // 남은 목표 k 값
        for(int i=0; i<n; i++){
            // 현재 위치에 A를 반드시 배치해야 하는 경우
            // 남은 A가 있고 현재 자리에 B를 배치했을 때, 남은 문자들로 remainK를 채울 수 없는 경우
            if(remainA > 0 && remainK > remainA * (remainB - 1)){
                // 'A'를 배치하여 remainB만큼 교차 횟수를 확보합니다.
                sb.append('A');
                remainK -= remainB;
                remainA--;
            }
            // 남은 B가 있고, A를 배치하지 않아도 목표 k를 달성할 수 있는 경우
            else if(remainB > 0){
                sb.append('B');
                remainB--;
            }
            // 남은 B는 없고 A만 남은 경우 남은 자리에 A를 모두 배치
            else{
                sb.append('A');
                remainA--;
            }
        }
        System.out.println(sb);
    }
}