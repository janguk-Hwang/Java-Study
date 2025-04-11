import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int m, n, k;
    static String[][] matrix;
    static int[][][] prefixSum;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        matrix = new String[m][n];
        prefixSum = new int[m + 1][n + 1][3];   // 0: 정글, 1: 바다, 2: 얼음
        for (int i = 0; i < m; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                matrix[i][j] = String.valueOf(line.charAt(j));
            }
        }

        // 누적합 구하기
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int t = 0; t < 3; t++) {
                    // 누적합은 누적합으로 구한다.
                    prefixSum[i][j][t] = prefixSum[i - 1][j][t] + prefixSum[i][j - 1][t] - prefixSum[i - 1][j - 1][t];
                }

                if (matrix[i - 1][j - 1].equals("J")) {
                    prefixSum[i][j][0]++;
                } else if (matrix[i - 1][j - 1].equals("O")) {
                    prefixSum[i][j][1]++;
                } else {
                    prefixSum[i][j][2]++;
                }
            }
        }

        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for(int t=0; t<3; t++){
                int cnt = prefixSum[x2][y2][t] - prefixSum[x1 - 1][y2][t] - prefixSum[x2][y1 - 1][t] + prefixSum[x1 - 1][y1 - 1][t];
                sb.append(cnt).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}