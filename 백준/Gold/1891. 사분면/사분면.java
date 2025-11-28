import java.io.*;
import java.util.*;

public class Main {
    static long x, y, moveCol, moveRow, totalSize;
    static char[] arr;
    static int size;
    static String s;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        size = Integer.parseInt(st.nextToken());
        s = st.nextToken();
        arr = new char[size];
        for(int i=0; i<size; i++) arr[i] = s.charAt(i);
        st = new StringTokenizer(br.readLine());
        moveCol = Long.parseLong(st.nextToken());
        moveRow = Long.parseLong(st.nextToken());
        x = 0; y = 0;
        int depth = size;
        for(char c : arr){
            depth--;
            long half = 1L << depth;
            // depth를 감소하면서 1, 2, 3, 4분면 중 어디인지 보고 그에 맞게 x, y 좌표 누적
            if(c == '1'){
                x += half;
                y += half;
            }
            else if(c == '2') y += half;
            // 3은 그대로
            else if(c == '4') x += half;
        }
        x += moveCol; y += moveRow;
        totalSize = 1L << size;
        if(x < 0 || x >= totalSize || y < 0 || y >= totalSize) {
            System.out.println(-1);
            return;
        }
        long curX = x; long curY = y;
        for(int i=0; i<size; i++){
            long boundary = 1L << (size - i - 1);
            if(boundary <= curX && boundary <= curY){
                sb.append('1');
                curX -= boundary;
                curY -= boundary;
                continue;
            }
            if(curX < boundary && boundary <= curY){
                sb.append('2');
                curY -= boundary;
                continue;
            }
            if(curX < boundary){
                sb.append('3');
                continue;
            }
            sb.append('4');
            curX -= boundary;
        }
        System.out.print(sb);
    }
}