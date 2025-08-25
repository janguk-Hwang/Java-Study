import java.io.*;
import java.util.*;

// 1번 스위치를 누르는 경우, 누르지 않는 경우 둘 다 진행
// 한 칸씩 초기 전구 상태를 목표 전구 상태와 비교하면서 다르면 i+1번째 스위치를 누름
// i+1번째 스위치를 누르기 때문에 완전하게 반대인 경우에 대해 스위치를 한 번만 눌러도 처리 됨.
// ex) 111101 -> 111010
// 4번 전구가 다르므로 5번째 스위치를 누른다. 111101이 정확히 111010로 바뀐다.

// ex) 111110 -> 111010
// 5번째 스위치를 눌러 111011으로 바뀜. 111011에서 111010으로 만들 수 없음(불가능)
public class Main {
    static int n;
    static int rst = Integer.MAX_VALUE;
    static int[][] light;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        light = new int[n][2];
        for(int i=0; i<2; i++){
            String[] s = br.readLine().split("");
            for(int j=0; j<s.length; j++) light[j][i] = Integer.parseInt(s[j]);
        }
        // 1번 전구의 상태를 바꾸는 방법은 1번 스위치를 누르는 경우와 2번 스위치를 누르는 경우로 나뉜다.
        func(true);
        func(false);
        if(rst == Integer.MAX_VALUE) System.out.print(-1);
        else System.out.print(rst);
    }

    public static void func(boolean firstSwitch){
        int cnt = 0;
        int[] tempLight = new int[n];
        // 초기 상태 복사
        for(int i=0; i<n; i++) tempLight[i] = light[i][0];
        // 첫 번째 스위치를 누르는 경우
        if(firstSwitch){
            press(tempLight, 0);
            cnt++;
        }
        for(int i=1; i<n; i++){
            // 상태가 다르면 i+1번째 스위치 누름
            if(tempLight[i-1] != light[i-1][1]){
                press(tempLight, i);
                cnt++;
            }
        }
        boolean flag = true;
        for(int i=0; i<n; i++){
            // 목표 상태와 비교, 다르면 flag를 false로
            if(tempLight[i] != light[i][1]){
                flag = false;
                break;
            }
        }
        if(flag) rst = Math.min(rst, cnt);
    }

    public static void press(int[] tempLight, int idx){
        for(int i=idx-1; i<=idx+1; i++){
            // 유효한 범위에 대해서만 상태 반전
            if(i >= 0 && i < n){
                tempLight[i] = tempLight[i] == 0 ? 1 : 0;
            }
        }
    }
}