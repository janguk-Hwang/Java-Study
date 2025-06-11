import java.io.*;
import java.util.*;

public class Main {
    static int n, m, r;
    static int[][] arr;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        int floor = Math.min(n, m) / 2;
        for(int i=0; i<floor; i++){
            int length = 2 * ((n - 2 * i) + (m - 2 * i)) - 4;
            int[] temp = new int[length];
            int idx = 0;

            for(int j=i; j<m-i; j++) temp[idx++] = arr[i][j];
            for(int j=i+1; j<n-i-1; j++) temp[idx++] = arr[j][m-i-1];
            for(int j=m-i-1; j>=i; j--) temp[idx++] = arr[n-i-1][j];
            for(int j=n-i-2; j>i; j--) temp[idx++] = arr[j][i];

            int realRotate = r % length;
            int[] rotated = new int[length];
            for(int k=0; k<length; k++) rotated[k] = temp[(k+realRotate) % length];

            idx = 0;
            for(int j=i; j<m-i; j++) arr[i][j] = rotated[idx++];
            for(int j=i+1; j<n-i-1; j++) arr[j][m-i-1] = rotated[idx++];
            for(int j=m-i-1; j>=i; j--) arr[n-i-1][j] = rotated[idx++];
            for(int j=n-i-2; j>i; j--) arr[j][i] = rotated[idx++];
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}