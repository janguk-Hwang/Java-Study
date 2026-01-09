import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 10_007;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        int n = Integer.parseInt(br.readLine());
        if(n <= 3){
            System.out.print(n);
            return;
        }
        int threeCnt = 0;
        int lastNum = 0;
        if(n % 3 == 0){
            threeCnt = n / 3;
            lastNum = 1;
        }
        else if(n % 3 == 1){
            threeCnt = n / 3 - 1;
            lastNum = 4;
        }
        else{
            threeCnt = (n-2) / 3;
            lastNum = 2;
        }
        long rst = 1L;
        for(int i=0; i<threeCnt; i++) rst = rst * 3 % MOD;
        rst *= lastNum;
        System.out.print(rst %= MOD);
    }
}