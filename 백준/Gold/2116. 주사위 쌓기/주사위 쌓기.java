import java.io.*;
import java.util.*;

public class Main {
    static int[] opp = {5, 3, 4, 1, 2, 0};
    static int[][] dice;
    static int n;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        dice = new int[n][6];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<6; j++){
                dice[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int max = 0;
        for(int i=0; i<6; i++){
            max = Math.max(max, calc(i));
        }
        System.out.print(max);
    }

    // 주사위 n개의 옆면 최대 숫자 합 계산
    static int calc(int topIdx){
        int bottomIdx = opp[topIdx];
        int topValue = dice[0][topIdx];
        int sum = maxSide(dice[0], topIdx, bottomIdx);

        for(int i=1; i<n; i++){
            int botIndex = findIdx(dice[i], topValue);
            int topIndex = opp[botIndex];
            topValue = dice[i][topIndex];
            sum += maxSide(dice[i], botIndex, topIndex);
        }
        return sum;
    }

    // 옆면의 최대값 반환
    static int maxSide(int[] d, int a, int b){
        int max = 0;
        for(int i=0; i<6; i++){
            if(i != a && i != b){
                max = Math.max(max, d[i]);
            }
        }
        return max;
    }

    // 주사위 번호 반환
    static int findIdx(int[] d, int val){
        for(int i=0; i<6; i++){
            if(d[i] == val) return i;
        }
        return -1;
    }
}