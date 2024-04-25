import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        
        String[][] matrix = new String[N][M];
        
        for(int i = 0; i < N; i++) {
            String[] str = sc.next().split("");
            for(int j = 0; j < M; j++) {
                matrix[i][j] = str[j];
            }
        }
        
        String[][] startBlack = {
            {"W","B","W","B","W","B","W","B"},
            {"B","W","B","W","B","W","B","W"},
            {"W","B","W","B","W","B","W","B"},
            {"B","W","B","W","B","W","B","W"},
            {"W","B","W","B","W","B","W","B"},
            {"B","W","B","W","B","W","B","W"},
            {"W","B","W","B","W","B","W","B"},
            {"B","W","B","W","B","W","B","W"}
        };

        String[][] startWhite = {
            {"B","W","B","W","B","W","B","W"},
            {"W","B","W","B","W","B","W","B"},
            {"B","W","B","W","B","W","B","W"},
            {"W","B","W","B","W","B","W","B"},
            {"B","W","B","W","B","W","B","W"},
            {"W","B","W","B","W","B","W","B"},
            {"B","W","B","W","B","W","B","W"},
            {"W","B","W","B","W","B","W","B"}
        };

        int minimum = Integer.MAX_VALUE;
        
        for (int n = 0; n < N - 7; n++) {
            for (int m = 0; m < M - 7; m++) {
                int cnt = 0;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (!matrix[n + i][m + j].equals(startBlack[i][j])) {
                            cnt++;
                        }
                    }
                }
                minimum = Math.min(minimum, cnt);
                
                cnt = 0;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (!matrix[n + i][m + j].equals(startWhite[i][j])) {
                            cnt++;
                        }
                    }
                }
                minimum = Math.min(minimum, cnt);
            }
        }
        System.out.println(minimum);
    }
}