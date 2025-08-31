import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(br.readLine());
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                set.add(arr[i] + arr[j]);
            }
        }
        Arrays.sort(arr);   // 정렬해야 최대값부터 검사됨
        for(int i=n-1; i>=0; i--){
            for(int j=0; j<i; j++){
                int target = arr[i] - arr[j];   // 찾아야 하는 두 수의 합
                if(set.contains(target)){
                    System.out.print(arr[i]);
                    return;
                }
            }
        }
    }
}