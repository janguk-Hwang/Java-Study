import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int[] input = {0, 0};
        while(true){
            String[] in = br.readLine().split(" ");
            for(int i=0; i<2; i++){
                input[i] = Integer.parseInt(in[i]);    
            }
            int x = input[0];
            int y = input[1];
            
            if((x==0) && (y==0)){
                break;
            }
            
            if(x>y){
                if((x%y)==0){
                    bw.write("multiple");
                    bw.newLine();
                }
                if((x%y)!=0){
                    bw.write("neither");
                    bw.newLine();
                }
            }
            else if(x<y){
                if((y%x)==0){
                    bw.write("factor");
                    bw.newLine();
                }
                if((y%x)!=0){
                    bw.write("neither");
                    bw.newLine();
                }
            }
            bw.flush();
        }
        bw.close();
    }
}



