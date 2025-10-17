import java.io.*;
import java.util.*;

public class Main {
    static boolean[] visited = new boolean[100_000];
    static Queue<int[]> q = new LinkedList<>();
    static int[] arr = new int[2];
    static int n, t, g;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        if(n == g){
            System.out.print(0);
            return;
        }
        q.add(new int[]{n, 0});
        visited[n] = true;
        while(!q.isEmpty()){
            int[] temp = q.poll();
            if(temp[1] >= t) continue;
            arr[0] = temp[0] + 1;
            arr[1] = buttonB(temp[0]);
            for(int x : arr){
                if(x == -1 || x >= 100_000) continue;
                if(visited[x]) continue;
                if(x == g){
                    System.out.print(temp[1] + 1);
                    return;
                }
                visited[x] = true;
                q.add(new int[]{x, temp[1] + 1});
            }
        }
        System.out.print("ANG");
    }

    static int buttonB(int num){
        if(num == 0) return 0;
        num *= 2;
        if(num >= 100_000) return -1;
        int pow = 1;
        while(num >= pow * 10) pow *= 10;
        num -= pow;
        return num;
    }
}