import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] S = br.readLine().split(" ");
        int N = Integer.parseInt(S[0]);
        int M = Integer.parseInt(S[1]);
        int[][] matrixA = new int[N][M];
        int[][] matrixB = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().trim().split(" ");
            for (int j = 0; j < M; j++) {
                matrixA[i][j] = Integer.parseInt(str[j]);
            }
        }
        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().trim().split(" ");
            for (int j = 0; j < M; j++) {
                matrixB[i][j] = Integer.parseInt(str[j]);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                bw.write(String.valueOf(matrixA[i][j] + matrixB[i][j]) + " ");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}