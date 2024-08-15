import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int M = Integer.parseInt(in[1]);

        //배열 입력받아서 넣기
        String[] input = br.readLine().split(" ");
        int[] arr1 = new int[N+M];
        for(int i=0; i<N; i++){
             arr1[i] = Integer.parseInt(input[i]);
        }
        String[] input1 = br.readLine().split(" ");
        for(int i=0; i<M; i++){
            arr1[i+N] = Integer.parseInt(input1[i]);
        }

        Arrays.sort(arr1);

        for(int i=0; i<arr1.length; i++){
            bw.write(String.valueOf(arr1[i]) + " ");
        }

        bw.flush();
        bw.close();
    }
}