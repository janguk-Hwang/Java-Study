import java.util.*;
import java.io.*;

// 하 문제
public class Main {
    static int n, count;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        Point[] point = new Point[n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            point[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        count = 0;
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                for(int k=j+1; k<n; k++){
                    Point a = point[i];
                    Point b = point[j];
                    Point c = point[k];
                    if((dot(a, b, c) == 0) || (dot(b, a, c) == 0) || (dot(c, a, b) == 0)){
                        count++;
                    }
                }
            }
        }

        System.out.println(count);
    }

    // 내적 결과 반환 함수 (두 벡터를 구하고 x성분끼리, y성분끼리 곱해서 더한다)
    // 좌표의 절댓값은 10^9, t1,2,3,4의 최댓값은 2*10^9, return의 최대는 8*10^18라서 long으로 커버 가능
    static long dot(Point a, Point b, Point c){
        long t1 = b.x - a.x;
        long t2 = b.y - a.y;
        long t3 = c.x - a.x;
        long t4 = c.y - a.y;

        return t1*t3 + t2*t4;
    }

    static class Point{
        int x, y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}