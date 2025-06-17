import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static TreeSet<Integer> set = new TreeSet<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        if(n == 1){
            System.out.print(0);
            return;
        }
        long total = 0;
        int current = 0;
        while(n-- > 0){
            st = new StringTokenizer(br.readLine());
            if(Integer.parseInt(st.nextToken()) == 1){
                int trash = Integer.parseInt(st.nextToken());
                set.add(trash);
                continue;
            }
            while(!set.isEmpty()){
                Integer left = set.floor(current);
                Integer right = set.ceiling(current);
                int leftDist = Integer.MAX_VALUE;
                int rightDist = Integer.MAX_VALUE;
                if(left != null) leftDist = Math.abs(current - left);
                if(right != null) rightDist = Math.abs(current - right);

                int next = 0;
                if(leftDist <= rightDist) next = left;
                if(rightDist < leftDist) next = right;

                total += Math.abs(current - next);
                current = next;
                set.remove(next);
            }
        }
        System.out.print(total);
    }
}