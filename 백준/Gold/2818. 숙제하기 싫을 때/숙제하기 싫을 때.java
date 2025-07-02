import java.io.*;
import java.util.*;

public class Main {
    static int r, c;
    static long totalSum;
    static int[] dice = {1, 6, 2, 5, 4, 3}; // top, bottom, front, back, left, right
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        if (r == 1 && c == 1) {
            System.out.print(1);
            return;
        }
        totalSum = 0;
        for(int row=0; row<r; row++){
            totalSum += dice[0];
            int[] tempDice = Arrays.copyOf(dice, dice.length);
            long cycleSum = 0;
            int[] cycleValues = new int[4];
            if(row % 2 == 0){
                for(int i=0; i<4; i++){
                    right(tempDice);
                    cycleValues[i] = tempDice[0];
                    cycleSum += tempDice[0];
                }
                int remainingCols = c - 1;
                totalSum += (long) (remainingCols / 4) * cycleSum;
                for(int i=0; i<remainingCols%4; i++){
                    right(dice);
                    totalSum += dice[0];
                }
            }
            if(row % 2 != 0){
                for(int i=0; i<4; i++){
                    left(tempDice);
                    cycleValues[i] = tempDice[0];
                    cycleSum += tempDice[0];
                }
                int remainingCol = c - 1;
                totalSum += (long)(remainingCol / 4) * cycleSum;
                for(int i = 0; i< remainingCol % 4; i++){
                    left(dice);
                    totalSum += dice[0];
                }
            }
            if(row<r-1) down(dice);
        }
        System.out.println(totalSum);
    }

    static void right(int[] d) {
        int temp = d[0];
        d[0] = d[4];
        d[4] = d[1];
        d[1] = d[5];
        d[5] = temp;
    }

    static void left(int[] d) {
        int temp = d[0];
        d[0] = d[5];
        d[5] = d[1];
        d[1] = d[4];
        d[4] = temp;
    }

    static void down(int[] d) {
        int temp = d[0];
        d[0] = d[3];
        d[3] = d[1];
        d[1] = d[2];
        d[2] = temp;
    }
}