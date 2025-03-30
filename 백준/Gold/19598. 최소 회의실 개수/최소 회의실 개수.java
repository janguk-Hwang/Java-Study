import java.util.*;
import java.io.*;

public class Main {
    static StringTokenizer st;
    static int n;
    static int[][] meetings;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        meetings = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            // 회의의 시작시간과 끝나는 시간
            meetings[i][0] = Integer.parseInt(st.nextToken());
            meetings[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(minMeetingRooms(meetings));
    }

    public static int minMeetingRooms(int[][] meetings) {
        // 시작 시간을 기준으로 정렬
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));

        // 종료 시간을 저장할 최소 힙
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int[] meeting : meetings) {
            // 가장 빨리 끝나는 회의보다 새 회의의 시작 시간이 같거나 크다면 회의실 재사용 가능
            if (!pq.isEmpty() && pq.peek() <= meeting[0]) {
                pq.poll();
            }
            // 새 회의의 종료 시간 추가
            pq.offer(meeting[1]);
        }

        return pq.size(); // 필요한 회의실 개수
    }
}
