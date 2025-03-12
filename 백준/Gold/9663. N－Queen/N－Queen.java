import java.util.Scanner;

public class Main {
    static int n, count = 0; // 체스판 크기(n)와 가능한 배치 수(count)
    static int[] temp; // 각 열에 배치된 퀸의 행 번호를 저장하는 배열

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt(); // 사용자로부터 체스판 크기(n) 입력 받기
        scanner.close();

        temp = new int[n]; // 크기 n인 배열 생성 (퀸의 위치 저장)

        // 첫 번째 열에 퀸을 배치할 모든 가능한 행에 대해 시도
        for (int i = 0; i < n; i++) {
            temp[0] = i; // 첫 번째 열(0)에 i번째 행에 퀸 배치
            nQueen(1); // 두 번째 열부터 퀸을 배치하는 백트래킹 함수 호출
            //System.out.println(count);
        }

        System.out.println(count); // 가능한 경우의 수 출력
    }

    static void nQueen(int col) {
        // 모든 열에 퀸을 배치했다면 유효한 경우이므로 count 증가 후 종료
        if (col == n) {
            count++;
            return;
        }

        // 현재 열(col)에 대해 모든 행(i)에 퀸을 배치 시도
        for (int i = 0; i < n; i++) {
            boolean isPossible = true;

            // 퀸을 col번째 열의 i번째 행에 놓아봄
            temp[col] = i;

            // 이전 열(j)에 있는 퀸들과 비교하여 공격 범위인지 확인
            for (int j = 0; j < col; j++) {
                // 같은 행에 퀸이 있거나, 대각선 상에 위치하면 배치 불가능
                if (temp[j] == temp[col] || Math.abs(j - col) == Math.abs(temp[j] - temp[col])) {
                    isPossible = false;
                    break; // 가지치기(더 이상 계산하지 않는다)
                }
            }

            // 배치 가능하다면 다음 열로 이동 (백트래킹)
            if (isPossible) {
                nQueen(col + 1);
            }
        }
    }
}
