import java.io.*;
import java.util.*;

public class Main {
    static int start;
    static int end;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] ice = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ice[i][0] = Integer.parseInt(st.nextToken());   //얼음의 양
            ice[i][1] = Integer.parseInt(st.nextToken());   //양동이 좌표
        }

        Arrays.sort(ice, (o1, o2) -> o1[1] - o2[1]);    //양동이 좌표 기준으로 오름차순 정렬

        start = 0;
        end = 0;
        int sum = ice[0][0];
        int result = 0;

        while (end < N) {
            //양동이가 자리잡을 수 있다면
            if(ice[end][1] - ice[start][1] <= 2 * K) {
                result = Math.max(result, sum);
                if(++end < N){
                    sum += ice[end][0];
                }
            }
            //양동이가 자리잡을 수 없다면
            else {
                sum -= ice[start++][0];
            }
        }
        System.out.println(result);
    }
}