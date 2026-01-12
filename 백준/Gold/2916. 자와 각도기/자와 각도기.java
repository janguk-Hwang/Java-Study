import java.io.*;
import java.util.*;

// 예를 들어 30, 70이 있으면 30끼리 더하거나 빼거나 30과 70을 더하거나 빼거나 70끼리 더하거나 빼거나 모두 가능하다.
// 결과들은 모두 두 수의 gcd의 배수들이다.
// 예제 2번에서 100과 360의 gcd는 20으로 60은 20의 배수이므로 가능하다.
// 1500 - 1440(360x4) = 60 -> 이 식에서 알 수 있듯이 360도 만들 수 있는 각이라고 생각해야 한다.
public class Main {
    static int n, k;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
        int rst = 360;
        for(int temp : arr) rst = gcd(rst, temp);
        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            if(Integer.parseInt(st.nextToken()) % rst == 0) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.print(sb);
    }

    static int gcd(int a, int b){
        while(b != 0){
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}