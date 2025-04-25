import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static long m;
    static long total, backLength;
    static PriorityQueue<int[]> left = new PriorityQueue<>((a, b) -> b[0] - a[0]);
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Long.parseLong(st.nextToken());
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if(start > end) left.add(new int[]{start, end});
        }

        backLength = 0; // 총 역방향 이동 거리 누적 변수

        if (left.isEmpty()) total = m; // 역방향 이동 없으면 총 거리는 m
        else {
            int[] t = left.poll();
            int curs = t[0];
            int cure = t[1];
            while (!left.isEmpty()) {
                int[] tem = left.poll();
                int ns = tem[0];
                int ne = tem[1];
                if (ns >= cure) cure = Math.min(cure, ne);
                else {
                    backLength += (long) curs - cure;
                    curs = ns;
                    cure = ne;
                }
            }
            backLength += (long) curs - cure;
            total = m + 2 * backLength;
        }
        System.out.println(total);
    }
}