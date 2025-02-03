import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] arr;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int[] result = solution();
        System.out.println(result[0] + " " + result[1]);
    }

    public static int[] solution() {
        int start = 0, end = n - 1;
        int minSum = Integer.MAX_VALUE;
        int[] bestPair = new int[2];

        // (1) 음수 + 양수 혼합 (기본 투 포인터)
        while (start < end) {
            int sum = arr[start] + arr[end];

            if (Math.abs(sum) < minSum) {
                minSum = Math.abs(sum);
                bestPair[0] = arr[start];
                bestPair[1] = arr[end];
            }

            if (sum < 0) {
                start++;
            } else {
                end--;
            }
        }

        // (2) 음수끼리만 혼합
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] < 0 && arr[i + 1] < 0) {
                int sum = arr[i] + arr[i + 1];
                if (Math.abs(sum) < minSum) {
                    minSum = Math.abs(sum);
                    bestPair[0] = arr[i];
                    bestPair[1] = arr[i + 1];
                }
            }
        }

        // (3) 양수끼리만 혼합
        for (int i = n - 1; i > 0; i--) {
            if (arr[i] > 0 && arr[i - 1] > 0) {
                int sum = arr[i] + arr[i - 1];
                if (Math.abs(sum) < minSum) {
                    minSum = Math.abs(sum);
                    bestPair[0] = arr[i - 1];
                    bestPair[1] = arr[i];
                }
            }
        }

        return bestPair;
    }
}
