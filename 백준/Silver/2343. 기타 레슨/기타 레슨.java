import java.io.*;
import java.util.*;

public class Main {
    private static int n, m;
    private static int [] arr;
    private static int low, high;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        int sum = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            low = Math.max(low, arr[i]);
        }

        high = sum;
        System.out.println(binarySearch());
    }

    public static int binarySearch() {
        while (low <= high) {
            int mid = (low + high) / 2;
            int sum = 0, count = 1;

            for (int i = 0; i < n; i++) {
                if (sum + arr[i] > mid) {
                    sum = 0;
                    count++;
                }
                sum += arr[i];
            }

            if (count <= m) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}