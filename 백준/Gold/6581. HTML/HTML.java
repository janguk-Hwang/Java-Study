import java.util.*;
import java.io.*;

// 마지막 줄은 개행 문자로 끝난다.
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        sb = new StringBuilder();
        StringBuilder line = new StringBuilder();
        String str;

        while ((str = br.readLine()) != null) {
            // 여러 공백, 탭, 개행 문자 등을 하나의 공백으로 처리
            str = str.replaceAll("[\\s]+", " ");
            st = new StringTokenizer(str, " ", false);

            while (st.hasMoreTokens()) {
                String s = st.nextToken();

                if (s.equals("")) continue;

                if (s.equals("<br>")) {
                    sb.append(line.toString().trim()).append("\n");
                    line.setLength(0);
                } else if (s.equals("<hr>")) {
                    if (line.length() > 0) {
                        sb.append(line.toString().trim()).append("\n");
                        line.setLength(0);
                    }
                    sb.append("-".repeat(80)).append("\n");
                } else {
                    if (line.length() + s.length() + 1 > 80) {
                        sb.append(line.toString().trim()).append("\n");
                        line.setLength(0);
                    }
                    if (line.length() > 0) line.append(" ");
                    line.append(s);
                }
            }
        }

        // 마지막 줄 처리
        if (line.length() > 0) {
            sb.append(line.toString().trim()).append("\n");
        }

        System.out.print(sb.toString());
    }
}
