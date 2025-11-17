import java.io.*;
import java.util.*;

public class Main {
    static int[] wrongCol;
    static int r, c, k, wrong;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine());
        wrong = Integer.parseInt(br.readLine());
        wrongCol = new int[wrong];
        int maxRow = 0;
        for(int i=0; i<wrong; i++){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            wrongCol[i] = col;
            maxRow = Math.max(maxRow, row);
        }
        Arrays.sort(wrongCol);

        int start = maxRow-1; int end = c;
        while(start + 1 < end){
            int mid = (start + end) / 2;
            if(isPossible(mid)) end = mid;
            else start = mid;
        }
        System.out.println(end);
    }

    // mid 크기의 색종이 K장으로 모든 잘못 칠해진 칸을 덮을 수 있는지 검사
    static boolean isPossible(int size) {
        int count = 0;
        int idx = 0;
        while(idx < wrongCol.length) {
            int putRange = wrongCol[idx] + size - 1;
            while(idx < wrongCol.length && wrongCol[idx] <= putRange) idx++;
            count++;
        }
        return count <= k;
    }
}