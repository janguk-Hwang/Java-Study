import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int A = Integer.parseInt(input[0]);
        int P = Integer.parseInt(input[1]);

        ArrayList<Integer> list = new ArrayList<>();
        list.add(A);    //수열 D의 시작을 list에 추가

        while (true) {
            //마지막 값을 temp에 저장(다음 수열 값을 구하기 위해 필요한 D[n-1] 값)
            int temp = list.get(list.size()-1);

            int result = 0;     //수열 D[n-1]의 값을 저장할 변수
            //어떤 수에 대해서 각 자리에 대해 P제곱만큼하여 각 자리를 더한 값을 구함.
            while (temp != 0) { //0이 아닐때까지 각 자리수를 P제곱하여 result에 더한다
                result += (int) Math.pow(temp % 10, (double)P);
                temp /= 10;
            }

            //result가 이미 list에 있다면 사이클이 끝났다는 의미
            //해당 result의 인덱스를 반환.
            if(list.contains(result)) {
                System.out.println(list.indexOf(result));
                //끝났으므로 종료
                break;
            }

            //리스트에 수열 저장
            list.add(result);
        }
    }
}