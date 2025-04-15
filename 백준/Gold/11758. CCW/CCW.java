import java.util.*;
import java.io.*;

// (-10,000 ≤ x1, y1, x2, y2, x3, y3 ≤ 10,000)
public class Main {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int x3 = Integer.parseInt(st.nextToken());
        int y3 = Integer.parseInt(st.nextToken());

        int slope = (x2 - x1) * (y3 - y2) - (y2 - y1) * (x3 - x2);

        if (slope > 0) {
            System.out.println(1);  // 반시계 방향
        } else if (slope < 0) {
            System.out.println(-1); // 시계 방향
        } else {
            System.out.println(0);  // 일직선
        }
    }
}