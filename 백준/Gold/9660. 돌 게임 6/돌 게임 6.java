import java.util.*;
import java.io.*;

// 돌은 1개, 3개 또는 4개 가져갈 수 있다
// 마지막 돌을 가져가는 사람이 게임을 이기게 된다.
// 두 사람이 완벽하게 게임을 했을 때, 이기는 사람을 구하는 프로그램을 작성
// 게임은 상근이가 먼저 시작한다.
public class Main {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());

        if(n % 7 == 0 || n % 7 == 2){
            System.out.println("CY");
        } else{
            System.out.println("SK");
        }
    }
}
