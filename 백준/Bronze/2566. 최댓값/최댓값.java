import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[][] matrix = new int[9][9];
        int maximum = Integer.MIN_VALUE;
        int row = 0;
        int col = 0;

        // 행렬 입력 받기
        for (int i = 0; i < 9; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < 9; j++) {
                matrix[i][j] = Integer.parseInt(input[j]);
                // 최댓값 갱신
                if (matrix[i][j] > maximum) {
                    maximum = matrix[i][j];
                    row = i;
                    col = j;
                }
            }
        }

        // 최댓값 출력
        bw.write(maximum + "\n");
        bw.write((row + 1) + " " + (col + 1));
        
        br.close();
        bw.flush();
        bw.close();
    }
}
