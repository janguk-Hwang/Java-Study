import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] trial;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        trial = new int[n][3];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            trial[i][0] = Integer.parseInt(st.nextToken());
            trial[i][1] = Integer.parseInt(st.nextToken());
            trial[i][2] = Integer.parseInt(st.nextToken());
        }
        int total = 0;
        for(int i=123; i<=987; i++){
            if(!isValid(i)) continue;
            boolean possible = true;
            for(int[] t : trial){
                int[] result = check(i, t[0]);
                if(result[0] != t[1] || result[1] != t[2]){
                    possible = false;
                    break;
                }
            }
            if(possible) total++;
        }
        System.out.print(total);
    }

    static boolean isValid(int num) {
        int a = num / 100;
        int b = (num - a * 100) / 10;
        int c = num % 10;
        return a != b && b != c && a != c && a != 0 && b != 0 && c != 0;
    }

    static int[] check(int a, int b){
         String str1 = String.valueOf(a);
         String str2 = String.valueOf(b);
         int strike = 0; int ball = 0;

         for(int i=0; i<3; i++){
             if(str1.charAt(i) == str2.charAt(i)) strike++;
             else if(str2.contains(String.valueOf(str1.charAt(i)))) ball++;
         }
         return new int[]{strike, ball};
    }
}