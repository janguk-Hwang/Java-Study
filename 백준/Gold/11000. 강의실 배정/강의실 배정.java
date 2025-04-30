import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] lecture;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        lecture = new int[n][2];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            lecture[i][0] = Integer.parseInt(st.nextToken()); // 시작 시간
            lecture[i][1] = Integer.parseInt(st.nextToken()); // 종료 시간
        }
        Arrays.sort(lecture, (a, b) -> a[0] - b[0]);
        pq.add(lecture[0][1]);
        for(int i=1; i<n; i++){
            if (pq.peek() <= lecture[i][0]) pq.poll();
            pq.add(lecture[i][1]);
        }
        System.out.println(pq.size());
    }
}

