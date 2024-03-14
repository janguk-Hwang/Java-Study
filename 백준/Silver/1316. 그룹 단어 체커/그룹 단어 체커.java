import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int i = Integer.parseInt(br.readLine());
        int result = 0;
        HashSet<Character> set;
        for (int j = 0; j < i; j++) {
            set = new HashSet<>();
            String S = br.readLine();
            boolean isGroupWord = true;
            for (int k = 0; k < S.length(); k++) {
                char currentChar = S.charAt(k);
                if (set.contains(currentChar)) {
                    isGroupWord = false;
                    break;
                }
                set.add(currentChar);
                while (k + 1 < S.length() && currentChar == S.charAt(k + 1)) {
                    k++;
                }
            }
            if (isGroupWord) {
                result++;
            }
        }
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}
