import java.io.*;
import java.util.*;

public class Main {
    static int n, r, c, rst;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        recur((int)Math.pow(2, n), r, c);
        System.out.print(rst);
    }

    public static void recur(int size, int r, int c){
        // 기본 단위
        if(size == 1) return;
        // 1 사분면
        if(r < size/2 && c >= size/2){
            rst += size * size / 4;
            recur(size/2, r, c - size/2);
        }
        // 2 사분면
        else if(r < size/2 && c < size/2) recur(size/2, r, c);
        // 3 사분면
        else if(r >= size/2 && c < size/2){
            rst += (size * size / 4) * 2;
            recur(size/2, r - size/2, c);
        }
        // 4 사분면
        else{
            rst += (size * size / 4) * 3;
            recur(size/2, r - size/2, c - size/2);
        }
    }
}