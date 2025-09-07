import java.io.*;
import java.util.*;

// 에라토스테네스의 체로 9999999까지 수 중에 소수를 찾아낸다.
// 입력으로 만들 수 있는 수들이 소수인지 검사
public class Main {
    static final int Max = 10_000_000;
    static boolean[] isPrime = new boolean[Max];
    static Set<Integer> set;
    static char[] arr;
    static boolean[] visited;
    static int c;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        makePrime();
        c = Integer.parseInt(br.readLine());
        for(int i=0; i<c; i++){
            String s = br.readLine();
            arr = s.toCharArray();
            visited = new boolean[arr.length];
            set = new HashSet<>();
            comb("", 0);
            int rst = 0;
            for(Integer a : set) if(isPrime[a]) rst++;
            sb.append(rst).append("\n");
        }
        System.out.print(sb);
    }

    public static void comb(String str, int depth){
        if(str.length() > 0){
            int temp = Integer.parseInt(str);
            set.add(temp);
        }
        // 종료 조건
        if(depth == arr.length) return;
        // 선택
        for(int i=0; i<arr.length; i++){
            if(!visited[i]){
                visited[i] = true;
                comb(str + arr[i], depth+1);
                visited[i] = false;
            }
        }
    }

    // 에라토스테네스의 체
    public static void makePrime(){
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for(int i=2; i*i<Max; i++){
            if(isPrime[i]){
                for(int j=i*i; j<Max; j+=i){
                    isPrime[j] = false;
                }
            }
        }
    }
}