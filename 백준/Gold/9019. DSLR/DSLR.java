import java.io.*;
import java.util.*;

// D: D 는 n을 두 배로 바꾼다. 결과 값이 9999 보다 큰 경우에는 10000 으로 나눈 나머지를 취한다. 그 결과 값(2n mod 10000)을 레지스터에 저장한다.
// S: S 는 n에서 1 을 뺀 결과 n-1을 레지스터에 저장한다. n이 0 이라면 9999 가 대신 레지스터에 저장된다.
// L: L 은 n의 각 자릿수를 왼편으로 회전시켜 그 결과를 레지스터에 저장한다. 이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d2, d3, d4, d1이 된다.
// R: R 은 n의 각 자릿수를 오른편으로 회전시켜 그 결과를 레지스터에 저장한다. 이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d4, d1, d2, d3이 된다.
// 곱하기 2, 빼기 1, 회전 사용 가능
// 초기 값에서 D, S, L, R 동작에 따른 연산을 모두 해본다.
public class Main {
    static int T;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            visited = new boolean[10000];   // 0 ~ 9999
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            sb.append(func(A, B)).append("\n");
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }

    public static String func(int A, int B){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(A, ""));
        visited[A] = true;
        while(!q.isEmpty()){
            Node now = q.poll();
            if(now.num == B) return now.command;
            for(char com : new char[]{'D', 'S', 'L', 'R'}){
                int temp = calc(now.num, com);
                if(!visited[temp]){
                    visited[temp] = true;
                    q.offer(new Node(temp, now.command + com));
                }
            }
        }
        return "";
    }

    public static int calc(int num, int command){
        switch(command){
            case 'D' : return (num * 2) % 10000;
            case 'S' : return (num == 0) ? 9999 : num - 1;
            case 'L' : return (num % 1000) * 10 + num / 1000;
            case 'R' : return num / 10 + (num % 10) * 1000;
        }
        return -1;
    }

    public static class Node{
        int num; String command;
        public Node(int num, String command){
            this.num = num; this.command = command;
        }
    }
}