import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static ArrayList<int[]> event = new ArrayList<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            event.add(new int[]{s, 1}); // 시작: +1
            event.add(new int[]{e, -1}); // 끝: -1
        }
        event.sort((a, b) -> {
            if(a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });
        int cnt = 0, max = 0;
        for (int[] event : event) {
            cnt += event[1];
            max = Math.max(max, cnt);
        }
        System.out.println(max);
    }
}
