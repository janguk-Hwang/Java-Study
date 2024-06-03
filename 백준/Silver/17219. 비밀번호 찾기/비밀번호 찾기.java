import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String args[]) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	Scanner sc = new Scanner(System.in);
    	HashMap<String, String> note = new HashMap<>();
    	
    	String[] in = br.readLine().split(" ");
    	int N = Integer.parseInt(in[0]);
    	int M = Integer.parseInt(in[1]);
    	
        for(int i=0; i<N; i++) {
        	String input[] = br.readLine().split(" ");
        	note.put(input[0], input[1]);
        }
        for(int j=0; j<M; j++) {
        	String search_site = br.readLine();
        	bw.write(note.get(search_site));
        	bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}