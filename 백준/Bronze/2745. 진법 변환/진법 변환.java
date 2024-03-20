import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] input = br.readLine().split(" ");
        String S = input[0];
        int B = Integer.parseInt(input[1]);
        
        int sum = 0;
        for(int i = 0; i < S.length(); i++){
            char c = S.charAt(i);
            if(c <= 'Z' && c >= 'A'){
                sum += ((int)(c - 'A' + 10)) * Math.pow(B, S.length() - (i + 1));
            }
            else{
                //1~9
                sum += ((int)(c - '0')) * Math.pow(B, S.length() - (i + 1));
            }
        }
        
        bw.write(String.valueOf(sum));
        bw.flush();
        bw.close();
    } 
}
