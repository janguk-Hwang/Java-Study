import java.io.*;
import java.util.*;

// 언제든지 로봇이 내리는 위치에 도달하면 그 즉시 내린다
public class Main {
    static int n, k;
    static int[] dura;
    static boolean[] isRobot;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dura = new int[2 * n + 1];
        isRobot = new boolean[2 * n + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i< 2*n+1; i++) dura[i] = Integer.parseInt(st.nextToken()); // 1-base
        int step = 0;
        while(true){
            step++;
            rotate();   // 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
            moveRobot();    // 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
            if(dura[1] > 0){    // 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
                isRobot[1] = true;
                dura[1]--;
            }
            if(isBreak()) break;    // 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
        }
        System.out.println(step);
    }

    // 벨트를 회전시켜 로봇도 회전 시킴
    public static void rotate(){
        // 내구도 회전
        int temp1 = dura[2*n];
        for(int i=2*n; i>1; i--) dura[i] = dura[i - 1];
        dura[1] = temp1;

        // 로봇 배열 회전
        boolean temp2 = isRobot[2*n];
        for(int i=2*n; i>1; i--) isRobot[i] = isRobot[i - 1];
        isRobot[1] = temp2;
        isRobot[n] = false;   // 내리는 위치의 로봇은 내림
    }

    // 로봇 이동
    public static void moveRobot(){
        for(int i=n-1; i>=1; i--){
            // 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
            if(isRobot[i] && !isRobot[i + 1] && dura[i+1] > 0){
                isRobot[i+1] = true;
                isRobot[i] = false;
                dura[i+1]--;
            }
        }
        isRobot[n] = false;
    }

    // 내구도가 0인 칸의 개수가 k개 이상이면 true 반환
    public static boolean isBreak(){
        int cnt = 0;
        for(int i=1; i<=2*n; i++){
            if(dura[i] == 0) cnt++;
        }
        return cnt >= k;
    }
}