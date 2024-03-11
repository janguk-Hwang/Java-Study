import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] input = new int[N];
        int temp = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            input[i] = i + 1;
        }

        for (int a = 0; a < M; a++) {
            int i = sc.nextInt() - 1; // 인덱스 조정
            int j = sc.nextInt() - 1; // 인덱스 조정

            // 인덱스가 배열의 범위를 벗어나는지 확인
            if (i >= 0 && i < N && j >= 0 && j < N) {
                temp = input[i];
                input[i] = input[j];
                input[j] = temp;
            } else {
                System.out.println("Invalid index. Please provide valid indices within the array's bounds.");
                return; // 프로그램 종료
            }
        }

        for (int k = 0; k < N; k++) {
            System.out.print(input[k] + " ");
        }
    }
}
