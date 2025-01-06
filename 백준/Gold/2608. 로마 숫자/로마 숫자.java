import java.io.*;
import java.util.HashMap;

public class Main {
    static int sum;
    static int result;
    StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        HashMap<String, Integer> hashMap = new HashMap<>();
        sum = 0;

        hashMap.put("I", 1);
        hashMap.put("V", 5);
        hashMap.put("X", 10);
        hashMap.put("L", 50);
        hashMap.put("C", 100);
        hashMap.put("D", 500);
        hashMap.put("M", 1000);

        for(int j=0; j<2; j++){
            sum = 0;
            String[] input = br.readLine().split("");
            sum += hashMap.get(input[0]);
            for(int i=1; i<input.length; i++){
                //작은 숫자가 큰 숫자의 왼쪽에 오는 경우
                if(hashMap.get(input[i]) > hashMap.get(input[i-1])){
                    sum -= 2*hashMap.get(input[i-1]);
                    sum += hashMap.get(input[i]);
                }
                else{
                    sum += hashMap.get(input[i]);
                }
            }
            result += sum;
        }
        System.out.println(result);

        //result를 다시 로마숫자로 변환
        //입력된 각 수는 2000 보다 작거나 같고, 두 수의 합은 4000보다 작다.
        //천의 자리는 0부터 3까지 가능
        int thousand = result/1000;
        for(int i=0; i<thousand; i++){
            sb.append("M");
        }
        int hundred = (result-thousand*1000)/100;
        //백의 자리가 4 또는 9인 경우
        if((result-thousand*1000)/100 == 4){
            sb.append("C");
            sb.append("D");
        }
        if((result-thousand*1000)/100 == 9){
            sb.append("C");
            sb.append("M");
        }
        //백의 자리가 5인 경우
        if((result-thousand*1000)/100 == 5){
            sb.append("D");
        }
        //백의 자리가 0,1,2,3인 경우
        if((result-thousand*1000)/100 < 4){
            for(int i=0; i<(result-thousand*1000)/100; i++){
                sb.append("C");
            }
        }
        //백의 자리가 6,7,8인 경우
        if(hundred > 5 && hundred < 9){
            sb.append("D");
            for(int i=0; i<hundred-5; i++){
                sb.append("C");
            }
        }
        int ten = ((result-(thousand*1000))-hundred*100)/10;
        if(ten == 4){
            sb.append("X");
            sb.append("L");
        }
        if(ten == 9){
            sb.append("X");
            sb.append("C");
        }
        if(ten == 5){
            sb.append("L");
        }
        if(ten < 4){
            for(int i=0; i<ten; i++){
                sb.append("X");
            }
        }
        if(ten > 5 && ten < 9){
            sb.append("L");
            for(int i=0; i<ten-5; i++){
                sb.append("X");
            }
        }
        int one = result-(result/10)*10;
        if(one == 4){
            sb.append("I");
            sb.append("V");
        }
        if(one == 9){
            sb.append("I");
            sb.append("X");
        }
        if(one == 5){
            sb.append("V");
        }
        if(one < 4){
            for(int i=0; i<one; i++){
                sb.append("I");
            }
        }
        if(one > 5 && one < 9){
            sb.append("V");
            for(int i=0; i<one-5; i++){
                sb.append("I");
            }
        }
        System.out.println(sb);
    }
}