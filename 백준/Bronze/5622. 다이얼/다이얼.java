import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] input = br.readLine().split("");
        int sum = 0;
        //입력의 길이만큼 반복
        for(int i=0; i<input.length; i++){
            if((input[i].equals("A")) || (input[i].equals("B")) || (input[i].equals("C"))){
                sum += 3;
            }
            else if((input[i].equals("D")) || (input[i].equals("E")) || (input[i].equals("F"))){
                sum += 4;
            }
            else if((input[i].equals("G")) || (input[i].equals("H")) || (input[i].equals("I"))){
                sum += 5;
            }
            else if((input[i].equals("J")) || (input[i].equals("K")) || (input[i].equals("L"))){
                sum += 6;
            }
            else if((input[i].equals("M")) || (input[i].equals("N")) || (input[i].equals("O"))){
                sum += 7;
            }
            else if((input[i].equals("P")) || (input[i].equals("Q")) || (input[i].equals("R")) || (input[i].equals("S"))){
                sum += 8;
            }
            else if((input[i].equals("T")) || (input[i].equals("U")) || (input[i].equals("V"))){
                sum += 9;
            }
            else if((input[i].equals("W")) || (input[i].equals("X")) || (input[i].equals("Y")) || (input[i].equals("Z"))){
                sum += 10;
            }
        }
        bw.write(Integer.toString(sum));
        bw.flush();
        bw.close();
    }
}