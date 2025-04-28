import java.io.*;
import java.util.*;

public class Main {
    static int n, total;
    static int[][] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n][2];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[i][0] = x; arr[i][1] = y;
        }

        int curx = arr[0][0];
        int cury = arr[0][1];
        for(int i=1; i<n; i++){
            int nx = arr[i][0];
            int ny = arr[i][1];
            if(cury > nx) cury = Math.max(cury, ny);
            else{
                total += cury - curx;
                curx = nx;
                cury = ny;
            }
        }
        total += cury - curx;
        System.out.println(total);
    }
}