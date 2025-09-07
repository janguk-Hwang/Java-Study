import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static List<Integer> list = new ArrayList<>();      // 연결되어 있는 포트 번호를 담음
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];   // i번 포트가 연결되어야 하는 다른 포트의 번호
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
        for(int i=0; i<n; i++){
            int port = arr[i];
            int idx = Collections.binarySearch(list, port);
            idx = -(idx + 1);
            if(idx == list.size()){
                list.add(port);
            }
            else{
                list.set(idx, port);
            }
        }
        System.out.print(list.size());
    }
}