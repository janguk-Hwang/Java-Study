import java.io.*;
import java.util.*;

// 4자리까지의 소수를 모두 구해놓고 4자리를 0~9까지의 수로 바꿔보면서 소수이면 계속 진행
public class Main {
    static int t;
    static boolean[] isPrime = new boolean[10000];
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        prime();
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int rst = bfs(s, e);
            sb.append(rst == -1 ? "Impossible" : rst).append("\n");
        }
        System.out.print(sb);
    }

    public static int bfs(int s, int e){
        boolean[] visited = new boolean[10000];
        Queue<int[]> q = new LinkedList<>();    // 숫자, 횟수
        q.add(new int[]{s, 0});
        visited[s] = true;
        while(!q.isEmpty()){
            int[] now = q.poll();
            if(now[0] == e) return now[1];
            char[] arr = String.valueOf(now[0]).toCharArray();
            for(int i=0; i<4; i++){
                for(char c='0'; c<='9'; c++){
                    if(arr[i] == c) continue;   // 같은 숫자는 건너뜀
                    if(i == 0 && c == '0') continue; // 1000미만의 숫자는 건너뜀
                    char[] next = arr.clone();
                    next[i] = c;
                    int nextNum = Integer.parseInt(String.valueOf(next));
                    if(!visited[nextNum] && isPrime[nextNum]){
                        visited[nextNum] = true;
                        q.add(new int[]{nextNum, now[1] + 1});
                    }
                }
            }
        }
        return -1;
    }

    public static void prime(){
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for(int i=2; i*i<=9999; i++){
            // 소수의 배수를 false로(에라토스테네스의 체)
            if(isPrime[i]){
                for(int j=i*i; j<=9999; j+=i){
                    isPrime[j] = false;
                }
            }
        }
    }
}