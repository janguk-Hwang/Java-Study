import java.io.*;
import java.util.*;

public class Main {
    static int n, pos;
    static int[] arr;
    static ArrayList<Integer> list = new ArrayList<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
        for(int i : arr){
            pos = Collections.binarySearch(list, i);
            if(pos < 0) pos = -(pos + 1);
            if(pos == list.size()) list.add(i);
            else list.set(pos, i);
        }
        System.out.print(list.size());
    }
}