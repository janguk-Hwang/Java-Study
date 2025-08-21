import java.io.*;
import java.util.*;

// 단순히 건물의 높이를 비교하여 볼 수 있는지 판단하는 것이 아닌 기울기이다.
// 왼쪽 건물 기준 현재까지의 최소 기울기보다 더 작은 건물들만 볼 수 있다.
// 오른쪽 건물 기준 현재까지의 최대 기울기보다 더 큰 건물들만 볼 수 있다.
public class Main {
    static int n;
    static int[] building;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        building = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) building[i] = Integer.parseInt(st.nextToken());
        int maxCanVisible = 0;  // 가장 많은 고층 빌딩이 보이는 빌딩에서 보이는 빌딩의 수
        for(int i=0; i<n; i++){
            int visible = 0;
            // 왼쪽
            double minSlope = Double.MAX_VALUE;
            for(int j=i-1; j>=0; j--){
                double slope = (double)(building[j] - building[i]) / (j - i);
                if(slope < minSlope){
                    minSlope = slope;
                    visible++;
                }
            }

            // 오른쪽
            double maxSlope = -Double.MAX_VALUE;
            for(int j=i+1; j<n; j++){
                double slope = (double)(building[j] - building[i]) / (j - i);
                if(slope > maxSlope){
                    maxSlope = slope;
                    visible++;
                }
            }
            maxCanVisible = Math.max(maxCanVisible, visible);
        }
        System.out.print(maxCanVisible);
    }
}