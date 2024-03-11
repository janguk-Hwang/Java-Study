import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.lang.Math;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int maximum = Integer.MIN_VALUE;
        int minimum = Integer.MAX_VALUE;
        String[] input = br.readLine().split(" ");
        for(int i=0; i<N; i++){
            maximum = Math.max(Integer.parseInt(input[i]), maximum);
            minimum = Math.min(Integer.parseInt(input[i]), minimum);
        }
        bw.write((minimum) + " " + (maximum));
        bw.flush();
        bw.close();
    }
}