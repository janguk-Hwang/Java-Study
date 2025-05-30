import java.io.*;
import java.util.*;

public class Main {
    static String input;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        input = br.readLine();
        visited = new boolean[input.length()];
        func(0, input.length()-1);
        System.out.print(sb);
    }

    public static void func(int left, int right){
        if(left > right) return;
        int index = left;
        for(int i=left; i<=right; i++){
            if(input.charAt(index) > input.charAt(i)){
                index = i;
            }
        }
        visited[index] = true;

        for(int i=0; i<input.length(); i++){
            if(visited[i]){
                sb.append(input.charAt(i));
            }
        }
        sb.append("\n");

        func(index + 1, right);
        func(left, index - 1);
    }
}