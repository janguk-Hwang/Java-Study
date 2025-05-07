import java.io.*;
import java.util.*;

public class Main {
    static int n, c;
    static int[] weights;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        weights = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) weights[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(weights);
        // 1개 조합 확인
        if (Arrays.binarySearch(weights, c) >= 0) {
            System.out.println(1);
            return;
        }
        // 2개 조합 확인
        for (int i = 0; i < n; i++) {
            int target = c - weights[i];
            // binarySearch(배열 이름, 찾는 값)는 찾는 값의 인덱스를 반환한다.
            // 찾는 값이 없으면 -(해당 값이 들어갈 인덱스) - 1을 반환한다. 없으면 음수가 된다.
            int idx = Arrays.binarySearch(weights, target);
            if (idx >= 0 && i != idx) { // 중복 처리
                System.out.println(1);
                return;
            }
        }
        // 3개 조합 확인: 2중 for문 + 이분 탐색
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = weights[i] + weights[j];
                int target = c - sum;
                int idx = Arrays.binarySearch(weights, target);
                if (idx >= 0 && idx != i && idx != j) { // 중복 처리
                    System.out.println(1);
                    return;
                }
            }
        }
        System.out.println(0);
    }
}
