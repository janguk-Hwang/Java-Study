import java.io.*;
import java.util.*;

// 기하학의 냄새가 난다.
// 두 선분이 2차원 좌표에서 교차하는지 확인하는 방법은?
// CCW를 이용해서 풀 수 있다.
// CCW(Counter Clock Wise)는 3개의 점이 있을 때 이 세점을 이은 직선의 방향을 알고자 할 때 유용한 알고리즘이다
// CCW 알고리즘에서 순서가 중요하다. 순서에 따라 시계 방향인지 반시계 방향인지 달라진다.(양수, 음수)
// 점 A, B, C가 있다고 할 때
// AB벡터 = (B.x - A.x, B.y - A.y)
// AC벡터 = (C.x - A.x, C.y - A.y)
// 신발끈처럼 연결했을 때 오른쪽 방향으로 곱한 값은 +, 왼쪽 방향으로 곱한 값은 -
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        Point a = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        Point b = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        Point c = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        Point d = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        int ab_c = ccw(a, b, c);
        int ab_d = ccw(a, b, d);
        int cd_a = ccw(c, d, a);
        int cd_b = ccw(c, d, b);
        if(ab_c * ab_d < 0 && cd_a * cd_b < 0) System.out.print(1);
        else System.out.print(0);
    }

    // 반시계 - 양수 - 1반환, 시계 - 음수 - -1반환
    // ab, ac
    public static int ccw(Point a, Point b, Point c){
        long temp = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        // 세 점이 일직선 위에 있는 경우는 없다. -> temp가 0인 경우는 없다.
        return (temp > 0) ? 1 : -1;
    }

    public static class Point{
        long x; long y;
        public Point(long x, long y){
            this.x = x; this.y = y;
        }
    }
}