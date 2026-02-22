import java.io.*;
import java.util.*;

// 낮을수록 or 높을수록 or 중간일수록 파괴해야 하는 장애물의 수가 비례하여 달라지지는 않는다.
// 종유석, 석순이 어떤 높이로 난다면 몇 개가 파괴되는지를 누적합으로 계산해놓는다.
public class Main {
    static int n, h;
    static int[] prefixSumJ, prefixSumS;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        prefixSumJ = new int[h + 1];
        prefixSumS = new int[h + 1];
        for(int i=0; i<n; i++){
            int temp = Integer.parseInt(br.readLine());
            if(i % 2 == 0) prefixSumS[temp]++;
            else prefixSumJ[temp]++;
        }
        for(int i=h-1; i>=1; i--){
            prefixSumS[i] += prefixSumS[i + 1];
            prefixSumJ[i] += prefixSumJ[i + 1];
        }
        int min = Integer.MAX_VALUE;
        int cnt = 0;
        for(int i=1; i<=h; i++){
            int totalDestroy = prefixSumS[i] + prefixSumJ[h - i + 1];
            if(totalDestroy < min){
                min = totalDestroy;
                cnt = 1;
            }
            else if(totalDestroy == min) cnt++;
        }
        System.out.print(min + " " + cnt);
    }
}