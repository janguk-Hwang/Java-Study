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
        String S = br.readLine();
        while(true){
            if(S.contains("c=") || S.contains("c-") || S.contains("dz=") || S.contains("d-") || S.contains("lj") || S.contains("nj") || S.contains("s=") || S.contains("z=")){
                S = S.replace("c=", "a");
                S = S.replace("c-", "a");
                S = S.replace("dz=", "a");
                S = S.replace("d-", "a");
                S = S.replace("lj", "a");
                S = S.replace("nj", "a");
                S = S.replace("s=", "a");
                S = S.replace("z=", "a");
            }else{
                break;
            }
        }
        bw.write(Integer.toString(S.length()));
        bw.flush();
        bw.close();
    }
}