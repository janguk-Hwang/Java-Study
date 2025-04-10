import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        StringBuilder line = new StringBuilder();

        String inputLine;
        while ((inputLine = br.readLine()) != null) {
            // 입력 줄에서 \t, 여러 공백 포함된 것들 하나의 공백으로
            inputLine = inputLine.replaceAll("[\\s]+", " ");
            // 태그와 단어를 구분하기 위해 split
            String[] tokens = inputLine.split(" ");

            for (String token : tokens) {
                if (token.equals("")) continue; // 공백 무시

                if (token.equals("<br>")) {
                    output.append(line.toString().trim()).append("\n");
                    line.setLength(0);
                } else if (token.equals("<hr>")) {
                    if (line.length() > 0) {
                        output.append(line.toString().trim()).append("\n");
                        line.setLength(0);
                    }
                    output.append("-".repeat(80)).append("\n");
                } else {
                    if (line.length() + token.length() + 1 > 80) {
                        output.append(line.toString().trim()).append("\n");
                        line.setLength(0);
                    }
                    if (line.length() > 0) line.append(" ");
                    line.append(token);
                }
            }
        }
        if (line.length() > 0) {
            output.append(line.toString().trim()).append("\n");
        }

        System.out.print(output.toString());
    }
}
