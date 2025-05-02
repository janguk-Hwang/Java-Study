import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] arr;
    static int mod = 9901;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        n = Integer.parseInt(br.readLine());
        arr = new int[n][3];
        arr[0][0] = 1;
        arr[0][1] = 1;
        arr[0][2] = 1;
        for(int i=1; i<n; i++){
            arr[i][0] = (arr[i-1][0] + arr[i-1][1] + arr[i-1][2]) % mod;
            arr[i][1] = (arr[i-1][0] + arr[i-1][2]) % mod;
            arr[i][2] = (arr[i-1][0] + arr[i-1][1]) % mod;
        }
        System.out.println((arr[n-1][0] + arr[n-1][1] + arr[n-1][2]) % mod);
    }
}