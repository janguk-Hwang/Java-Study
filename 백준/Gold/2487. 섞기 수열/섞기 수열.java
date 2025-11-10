import java.io.*;
import java.util.*;

// 섞기 수열 -> 수열의 값을 인덱스로 갖는 카드를 위치시킨다.
// 1 2 3 4 5 6
// 3 2 5 6 1 4
// 5 2 1 4 3 6
// 1 2 3 6 5 4
// 3 2 5 4 1 6
// 5 2 1 6 3 4
// 1 2 3 4 5 6
// 각 사이클의 최소공배수
public class Main {
    static int n, rst;
    static int[] arr;
    static boolean[] visited;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        visited = new boolean[n + 1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) arr[i] = Integer.parseInt(st.nextToken());
        rst = 1;
        for(int i=1; i<=n; i++){
            if(!visited[i]){
                int length = 0;
                int cur = i;
                while(!visited[cur]){
                    visited[cur] = true;
                    length++;
                    cur = arr[cur];
                }
                rst = lcm(rst, length);
            }
        }
        System.out.print(rst);
    }

    static int lcm(int a, int b){
        return a / gcd(a, b) * b;
    }

    static int gcd(int a, int b){
        while(b != 0){
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}