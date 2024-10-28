import java.io.*;

public class Main {
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split("");

        //패턴 IOI---OI를 생성
        StringBuilder pattern = new StringBuilder("I");
        for (int i = 0; i < N; i++) {
            pattern.append("O").append("I");
        }

        cnt = 0;

        for (int i = 0; i <= M - pattern.length(); i++){   //pattern.length()는 IOIOIOI의 길이
            boolean flag = true;
            for (int j = 0; j < pattern.length(); j++) {
                if (!input[i + j].equals(String.valueOf(pattern.charAt(j)))){
                    //한 글자라도 pattern과 다르다면 flag는 false가 됨
                    flag = false;
                    //한 글자라도 다르면 한 칸뒤로 이동
                    break;
                }
            }
            //pattern이 있으면 cnt 증가
            if (flag) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
