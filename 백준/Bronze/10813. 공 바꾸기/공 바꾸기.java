import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] input = new int[N];
        int temp = 0;

        for (int i = 0; i < N; i++) {
            input[i] = i + 1;
        }
        for (int a = 0; a < M; a++) {
            int i = sc.nextInt() - 1;
            int j = sc.nextInt() - 1;
            temp = input[i];
            input[i] = input[j];
            input[j] = temp;
        }
        for (int k = 0; k < N; k++) {
            System.out.print(input[k] + " ");
        }
    }
}
