import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] store;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 가로
        m = Integer.parseInt(st.nextToken());   // 세로
        int temp = Integer.parseInt(br.readLine());
        store = new int[temp];
        int donggeun = 0;
        for(int i=0; i<temp+1; i++){
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            if(i == temp){
                donggeun = makeOneLine(dir, dist);
                break;
            }
            store[i] = makeOneLine(dir, dist);
        }
        int total = 0;
        int full = 2 * (n + m);
        for(Integer i : store){
            int tempDist = Math.abs(donggeun - i);
            total += Math.min(tempDist, full - tempDist);
        }
        System.out.print(total);
    }

    public static int makeOneLine(int dir, int dist){
        switch(dir){
            case 1: return dist;
            case 2: return 2 * n + m - dist;
            case 3: return 2 * (n + m) - dist;
            case 4: return n + dist;
            default: return 0;
        }
    }
}