import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[][] matrix = new int[9][9];
        String[] input = new String[9];
        int maximum = Integer.MIN_VALUE;
        int i,j = 0;
        int row = 0;
        int col = 0;
        for(i=0; i<9; i++){
            input = br.readLine().split(" ");
            for(j=0; j<9; j++){    
                matrix[i][j] = Integer.parseInt(input[j]);
                if(matrix[i][j] > maximum){
                    maximum = matrix[i][j];
                    row = i;
                    col = j;
                }
            }
        }
        bw.write(String.valueOf(maximum) + "\n");
        bw.write((row + 1) + " " + (col + 1));
        bw.flush();
        bw.close();
    }
}