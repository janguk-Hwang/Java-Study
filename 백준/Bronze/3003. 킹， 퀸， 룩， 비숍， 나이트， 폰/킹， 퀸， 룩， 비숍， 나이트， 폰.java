import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] S = br.readLine().split(" ");
        int[] input = new int[6];
        for(int j = 0; j<6; j++){
            input[j] = Integer.parseInt(S[j]);
        }
        int[] perfect = {1, 1, 2, 2, 2, 8};
        int[] result = new int[6];
        for(int i=0; i<6; i++){
            result[i] = perfect[i] - input[i];
        }
        for(int j=0; j<6; j++){
            bw.write(Integer.toString(result[j]) + " ");
        }
        bw.flush();
        bw.close();
    }
}