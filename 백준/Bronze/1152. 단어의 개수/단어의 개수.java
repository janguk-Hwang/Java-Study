import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] input = br.readLine().trim().split(" ");
        int len = input.length;
        if (input.length == 1 && input[0].isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(len);
        }
        //bw.write(Integer.toString(len));
        //bw.write(String.valueOf(len));
        bw.flush();
        bw.close();
    }
}
