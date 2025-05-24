import java.io.*;
import java.util.*;

public class Main {
    static int n, w, l;
    static int[] truck, bridge;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        truck = new int[n];
        bridge = new int[w];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<n; i++) truck[i] = Integer.parseInt(st.nextToken());
        int time = 0;
        int weight = 0;
        int index = 0;

        // 아직 출발하지 않은 트럭이 있거나 다리에 트럭이 있는 경우
        while(index < n || weight > 0){
            time++;
            // 다리의 맨 앞부분에 있는 무게를 제거
            weight -= bridge[0];
            // bridge[1]부터 bridge[w-1]까지(맨 앞부분 제외한 모든 다리 부분)의 다리를 한 칸씩 앞으로 이동
            for(int i=1; i<w; i++) bridge[i-1] = bridge[i];
            bridge[w-1] = 0;

            if(index < n && weight + truck[index] <= l){
                bridge[w-1] = truck[index];
                weight += truck[index];
                index++;
            }
        }
        System.out.print(time);
    }
}