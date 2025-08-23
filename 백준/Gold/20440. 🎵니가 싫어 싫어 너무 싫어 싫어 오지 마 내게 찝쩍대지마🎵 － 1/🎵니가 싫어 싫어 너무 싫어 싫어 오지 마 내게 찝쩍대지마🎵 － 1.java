import java.io.*;
import java.util.*;

// 모기들의 방 입장, 퇴장 시각이 주어졌을 때 모기들이 가장 많이 있는 시간대와 해당 시간대에 모기가 몇 마리가 있는지 구하는 프로그램을 만들어보자
// 시간대, 몇 마리인지 출력
public class Main {
    static int n;
    static int[][] t;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException{
        n = Integer.parseInt(br.readLine());
        t = new int[n][2];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            t[i][0] = Integer.parseInt(st.nextToken());     // 입장 시간
            t[i][1] = Integer.parseInt(st.nextToken());     // 퇴장 시간
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i=0; i<n; i++){
            // t[i] 시간에 몇 마리의 모기가 증가, 감소되는지 계산
            map.put(t[i][0], map.getOrDefault(t[i][0], 0) + 1);
            map.put(t[i][1], map.getOrDefault(t[i][1], 0) - 1);
        }
        int start = 0;
        int end = 0;
        int maxMosqCnt = 0;
        int curMosqCnt = 0;
        boolean flag = false;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int time = entry.getKey();
            int delta = entry.getValue();
            curMosqCnt += delta;
            // 새로운 최대 모기 수인 경우
            if(curMosqCnt > maxMosqCnt){
                maxMosqCnt = curMosqCnt;
                start = time;
                flag = true;    // 구간이 시작됨을 알리는 불 변수
            }
            // 모기 수가 최대치보다 적어진 경우(최대 모기 구간이 끝난 경우)
            else if(flag && curMosqCnt < maxMosqCnt){
                flag = false;
                end = time;
            }
        }
        System.out.println(maxMosqCnt);
        System.out.print(start + " " + end);
    }
}