import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int minL, maxL, maxH, tallest, area, maxUntilNow;
    static int[] height;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        height = new int[1001]; // 위치별 높이
        minL = 1001;
        maxL = 0;
        maxH = 0;
        tallest = 0;
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            height[L] = H;

            // 맨 왼쪽 인덱스, 맨 오른쪽 인덱스
            minL = Math.min(minL, L);
            maxL = Math.max(maxL, L);

            // 가장 높은 벽
            if (H > maxH) {
                maxH = H;
                tallest = L;
            }
        }

        // 맨 왼쪽에서부터 가장 높은 기둥까지
        area = 0;
        maxUntilNow = 0;
        // 높이가 높아지면 그만큼이 누적된다
        for(int i=minL; i<tallest; i++){
            maxUntilNow = Math.max(maxUntilNow, height[i]);
            area += maxUntilNow;
        }

        // 맨 오른쪽에서부터 가장 높은 기둥까지
        maxUntilNow = 0;
        for(int i=maxL; i>tallest; i--){
            maxUntilNow = Math.max(maxUntilNow, height[i]);
            area += maxUntilNow;
        }

        // 가장 높은 기둥 추가
        area += height[tallest];
        System.out.println(area);
    }
}
