import java.util.*;
import java.io.*;

public class Main {
    static double x, y, c;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        x = Double.parseDouble(st.nextToken());
        y = Double.parseDouble(st.nextToken());
        c = Double.parseDouble(st.nextToken());
        double start = 0.0;
        double end = Math.min(x, y);
        double mid = 0.0;
        while(end - start >= 0.001){
            mid = (start + end) / 2;
            double newC = calcC(x, y, mid);
            // 구한 c가 주어진 c보다 크면 건물 간의 거리를 늘려서 c를 낮춰야 한다.
            if(newC > c) start = mid;
            // newC와 c가 같아도 따로 처리하지 않고 이분탐색을 계속해서 오차가 1e-3 이하일 때까지 반복
            else end = mid;
        }
        System.out.println(mid);
    }

    public static double calcC(double x, double y, double dist){
        double h1 = Math.sqrt(x * x - dist * dist);
        double h2 = Math.sqrt(y * y - dist * dist);
        return h1 * h2 / (h1 + h2);
    }
}