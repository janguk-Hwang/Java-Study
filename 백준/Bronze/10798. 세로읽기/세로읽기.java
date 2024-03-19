import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input;
        char[][] matrix = new char[5][15]; 
        for(int i=0; i<5; i++) {
        	input = br.readLine();
        	for(int j=0; j<input.length(); j++) {
        		matrix[i][j] = input.charAt(j);
        	}
        }
        for(int j=0; j<matrix[0].length; j++) {
        	for(int i=0; i<5; i++) {
        		if(matrix[i][j] == '\0') {
        			continue;
        		}
        		bw.write(matrix[i][j]);
        	}
        }
        bw.flush();
        bw.close();
    }
}