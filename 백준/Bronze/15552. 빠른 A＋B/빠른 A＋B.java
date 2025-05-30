import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int s = Integer.parseInt(br.readLine()); //s에 정수 받아서 저장
        int A,B = 0;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i=0; i<s; i++){
            String[] sst = br.readLine().split(" ");
            A = Integer.parseInt(sst[0]);
            B = Integer.parseInt(sst[1]);
            bw.write((A+B) + "\n");
        }
        bw.flush();
        bw.close();
    }
}