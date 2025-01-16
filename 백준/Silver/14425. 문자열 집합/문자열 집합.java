import java.io.*;
import java.util.*;

public class Main {
    public static int sum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> hashmap = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            hashmap.put(st.nextToken(), 0);
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            if(hashmap.containsKey(st.nextToken())){
                sum++;
            }
        }

        System.out.print(sum);
    }
}