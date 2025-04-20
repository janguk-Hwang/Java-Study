import java.util.*;
import java.io.*;

// 상 문제
public class Main {
    static int n;
    static long m, max;
    static int[] tk;
    static long result = Long.MAX_VALUE;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());   // 입국심사대 수
        m = Integer.parseInt(st.nextToken());   // 상근이와 친구들의 명수
        tk = new int[n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            tk[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, tk[i]);
        }

        // 각 심사대의 심사 시간은 정해져있다.
        // 일정 시간동안 입국심사 처리를 할 수 있는 양은 정해져있다.
        // 시간을 조정하면서 m명이 입국심사를 마칠 수 있는 최소 시간을 구해보자
        long start = 0;
        long en = max * m;  // 가장 오래걸리는 심사대에 m명이 다 들어가는 경우
        while(start <= en){
            long mid = (start + en) / 2;
            long throughput = 0;
            long sum = 0;
            for(int i=0; i<n; i++){
                throughput = mid/tk[i];
                sum += throughput;  // mid 시간동안 처리할 수 있는 사람의 총합
                if(sum > m) break;
            }
            // 처리할 수 있는 사람의 수가 상근이 무리보다 크거나 같으면 시간을 더 줄인다.
            if(sum >= m){
                en = mid - 1;
            }
            else start = mid + 1;
        }
        System.out.println(start);
    }
}