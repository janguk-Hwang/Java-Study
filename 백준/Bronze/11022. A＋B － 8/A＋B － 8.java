import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //int T = br.read()-48;
        int T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++){
            String[] input = br.readLine().split(" ");
            int A = Integer.parseInt(input[0]);
            int B = Integer.parseInt(input[1]);
            bw.write("Case #" + (i+1) + ": " + A + " + " + B + " = " + (A+B) + "\n");
        }
        bw.flush();
        bw.close();
	 }
}