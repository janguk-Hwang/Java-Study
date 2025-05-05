import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] preMax, preMin;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        preMax = new int[3]; preMin = new int[3];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int[] point = new int[3];
            for (int j = 0; j < 3; j++) point[j] = Integer.parseInt(st.nextToken());
            int[] curMax = new int[3];
            int[] curMin = new int[3];

            curMax[0] = point[0] + Math.max(preMax[0], preMax[1]);
            curMax[1] = point[1] + Math.max(Math.max(preMax[0], preMax[1]), preMax[2]);
            curMax[2] = point[2] + Math.max(preMax[1], preMax[2]);

            curMin[0] = point[0] + Math.min(preMin[0], preMin[1]);
            curMin[1] = point[1] + Math.min(Math.min(preMin[0], preMin[1]), preMin[2]);
            curMin[2] = point[2] + Math.min(preMin[1], preMin[2]);

            preMax = curMax;
            preMin = curMin;
        }
        int maxResult = Math.max(Math.max(preMax[0], preMax[1]), preMax[2]);
        int minResult = Math.min(Math.min(preMin[0], preMin[1]), preMin[2]);
        System.out.print(maxResult + " " + minResult);
    }
}
