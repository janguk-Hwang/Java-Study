import java.io.*;
import java.util.*;

// 5 17 23 14 83
// 3+2 15+2 21+2 12+2 81+2
// 12 6 9 69
// 두 수의 차이들이 m의 배수이면 모두 m으로 나눴을 때, 나머지가 같다.
public class Main {
    static int n;
    static int[] arr;
    static List<Integer> list =  new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(br.readLine());
        Arrays.sort(arr);
        for(int i=1; i<n; i++) list.add(arr[i] - arr[i-1]);   // 차이
        int g = list.get(0);
        for(int i=1; i<list.size(); i++){
            g = gcd(g, list.get(i));    // 모든 차이의 gcd
        }
        // g의 약수
        List<Integer> divisor = new ArrayList<>();
        for(int i=2; i<=Math.sqrt(g); i++){
            if(g % i == 0){
                divisor.add(i);
                if(i != g / i) divisor.add(g / i);
            }
        }
        divisor.add(g);
        Collections.sort(divisor);
        for(Integer i : divisor) sb.append(i).append(" ");
        System.out.print(sb);
    }

    public static int gcd(int a, int b){
        while(b != 0){
            int temp = a%b; //나머지
            a = b;
            b = temp;
        }
        return a;   // 최대공약수(gcd)반환
    }
}