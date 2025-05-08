import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] arr;
    static Set<Integer> set = new HashSet<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
        int start = 0;
        long result = 0;
        for(int i=0; i<n; i++){
            if(set.contains(arr[i])){
                while(arr[start] != arr[i]){
                    set.remove(arr[start]);
                    start++;
                }
                start++;
            }
            else set.add(arr[i]);
            result += i - start + 1;
        }
        System.out.print(result);
    }
}