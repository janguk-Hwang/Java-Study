import java.io.*;
import java.util.*;

public class Main {
    static int result ;
    static boolean[] checkBool;
    static int[] numbers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        numbers = new int[]{1, 5, 10, 50};
        checkBool = new boolean[1001];
        int n = Integer.parseInt(br.readLine());

        backtracking(0, n, 0, 0);
        System.out.println(result);
    }

    private static void backtracking(int sum, int n, int depth, int index){
        if(depth == n){
            if (!checkBool[sum]){
                checkBool[sum] = true;
                result++;
            }
            return;
        }
        for(int i=index; i<4; i++){
            backtracking(sum + numbers[i], n, depth+1, i);
        }
    }
}