import java.io.*;
import java.util.*;

public class Main {
    static int n, time;
    static int[] p, s, arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        p = new int[n]; s = new int[n]; arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) p[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) s[i] = Integer.parseInt(st.nextToken());
        for(int i=0; i<n; i++) arr[i] = i;
        time = 0;
        while(true){
            if(isSame(arr, p)){
                System.out.println(time);
                return;
            }
            time++;
            int[] next = new int[n];
            for(int i=0; i<n; i++) next[s[i]] = arr[i]; // 카드 이동
            arr = next;
            // 초기 상태로 돌아왔는지 확인
            boolean flag = true;
            for(int i=0; i<n; i++){
                if(arr[i] != i){
                    flag = false;
                    break;
                }
            }
            // 한 사이클을 돌아 초기 상태로 돌아왔음에도 목표 상태에 도달할 수 없으면 -1 출력
            if(flag){
                System.out.println(-1);
                return;
            }
        }
    }

    // 각 카드가 원하는 플레이어(p[])에게 분배되었는지 검사
    static boolean isSame(int[] arr, int[] p){
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr.length; j++){
                // 각 카드의 순서를 찾고 해당 카드가 원하는 플레이어에게 도착했는지 검사
                if(arr[j] == i) if(j % 3 != p[i]) return false;
            }
        }
        return true;
    }
}