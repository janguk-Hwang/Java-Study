import java.io.*;

public class Main {
    static int[][] result;
    static int[][] cost;
    static int attribute;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            attribute = Integer.parseInt(br.readLine());
            cost = new int[2][attribute];
            
            for (int j = 0; j < 2; j++) {
                String[] input = br.readLine().split(" ");
                for (int k = 0; k < attribute; k++) {
                    cost[j][k] = Integer.parseInt(input[k]);
                }
            }

            result = new int[2][attribute]; // DP 결과를 저장할 배열
            //result 배열을 -1로 초기화 -> -1인 곳은 아직인 곳
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < attribute; k++) {
                    result[j][k] = -1;
                }
            }

            long answer = Math.max(dp(0, 0), dp(1, 0));
            System.out.println(answer); // 정답 출력
        }
    }
    
    public static int dp(int row, int col) {
        //열이 끝나면 종료 - dp 종료 조건
        if (col >= attribute) {
            return 0;
        }

        //메모이제이션: 이미 계산한 값이 있으면 그 값을 반환
        if (result[row][col] != -1) {
            return result[row][col];
        }

        //현재 위치에서 얻을 수 있는 최대값을 계산
        //한 칸 또는 두 칸 오른쪽으로 이동하는 경우 중 최대값 선택
        return result[row][col] = Math.max(dp((row + 1) % 2, col + 1) + cost[row][col],
                dp((row + 1) % 2, col + 2) + cost[row][col]);
    }
}