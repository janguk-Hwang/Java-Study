import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] input = br.readLine().split(" ");
        int[] pi = new int[N];

        for (int i = 0; i < N; i++) {
            pi[i] = Integer.parseInt(input[i]); // 각 사람이 돈을 인출하는데 걸리는 시간이 저장되어 있는 배열
        }

        Arrays.sort(pi); // 배열 정렬
        int[] sum = new int[N];

        int result = 0;

        for (int j = 0; j < N; j++) {
            for (int k = 0; k <= j; k++) {
                sum[j] += pi[k];
            }
            result += sum[j];
        }

        System.out.println(result);
    }
}