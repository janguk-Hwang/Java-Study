import java.io.*;

public class Main {
    static int[][] paper;
    static int minusone_num;
    static int zero_num;
    static int one_num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        minusone_num = 0;
        zero_num = 0;
        one_num = 0;

        int N = Integer.parseInt(br.readLine());
        paper = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(input[j]);
            }
        }

        papercut(0, 0, N, N);

        System.out.println(minusone_num);
        System.out.println(zero_num);
        System.out.println(one_num);
    }

    public static void papercut(int startrow, int startcol, int lastrow, int lastcol) {
        //종이의 일정 구역이 하나의 수로만 채워져 있는지 확인하는 로직
        boolean isPerfect = true;
        int firstValue = paper[startrow][startcol];

        for (int i = startrow; i < lastrow; i++) {
            if (!isPerfect) {
                break;
            }
            for (int j = startcol; j < lastcol; j++) {
                if (paper[i][j] != firstValue) {
                    isPerfect = false;
                    break;
                }
            }
        }

        //만약 모두 같은 숫자로 이루어져 있다면 해당 숫자 카운트 증가
        if (isPerfect) {
            if (firstValue == -1) {
                minusone_num++;
            } else if (firstValue == 0) {
                zero_num++;
            } else if (firstValue == 1) {
                one_num++;
            }
            return;
        }

        //모두 같은 숫자가 아니라면 종이를 9등분하여 재귀적으로 처리
        int midrow = (lastrow - startrow) / 3;
        int midcol = (lastcol - startcol) / 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                papercut(startrow + i * midrow, startcol + j * midcol, startrow + (i + 1) * midrow, startcol + (j + 1) * midcol);
            }
        }
    }
}