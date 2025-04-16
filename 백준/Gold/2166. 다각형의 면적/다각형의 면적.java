import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int[][] dot = new int[n + 1][2];    // [index][x or y]

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            dot[i][0] = Integer.parseInt(st.nextToken());
            dot[i][1] = Integer.parseInt(st.nextToken());
        }

        // 마지막 점은 시작 점과 같음
        dot[n][0] = dot[0][0];
        dot[n][1] = dot[0][1];

        long area = 0;
        // 다각형의 넓이는 삼각형들을 더해서 구할 수 있다.
        // 두 선을 외적하여 나온 값(평행사변형)을 2로 나눠 삼각형의 넓이를 누적시킨다.
        for(int i=0; i<n; i++){
            area += (((long) dot[i][0] * dot[i + 1][1]) - ((long) dot[i + 1][0] * dot[i][1]));
        }

        double result = Math.abs(area) / 2.0;
        System.out.printf("%.1f", result);
    }
}