import java.util.*;
import java.io.*;

public class Main {
    static int n, m, l;
    static List<Integer> restStop;  // 휴게소의 위치를 담는 리스트
    static StringTokenizer st;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        restStop = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i< n; i++){
            restStop.add(Integer.parseInt(st.nextToken()));
        }

        // 구간의 길이를 구하기 위해 시작과 끝 추가
        restStop.add(0);
        restStop.add(l);

        // 휴게소 위치에 따른 정렬
        Collections.sort(restStop);

        // 초기 시작, 끝 값
        int st = 1;
        int en = l-1;

        // 이미 휴게소 간 최대 거리의 최소값은 입력을 받으면 정해져 있다.
        // while문을 반복하다보면 st와 en이 변화하는데 st와 en사이에 휴게소 간 최대 거리의 최소값이 존재한다.
        // 이 값을 찾기 위해 이분 탐색을 진행한다. dist에 따른 세울 수 있는 휴게소의 개수를 보고 휴게소가 많이 세워졌으면
        // dist를 증가시켜 이분탐색 범위를 뒤쪽으로 조정, 반대로 휴게소를 더 세워야 한다면 dist를 줄이기 위해 이분탐색 범위를 앞쪽으로 조정
        // 이분 탐색을 하면서 st <= en이 만족할 때까지 진행하면 마지막에는 st와 en이 1차이가 나게되고 이때 st가 휴게소 간 최대 거리의 최소값이 된다.
        while(st <= en){
            int dist = (st + en) / 2; // 나눌 거리의 크기 (휴게소 간 최대거리의 상한선)
            int count = 0; // 세울 수 있는 휴게소의 개수

            // 휴게소가 지어질 수 있는 구간의 수 만큼 반복
            for(int i = 0; i< restStop.size()-1; i++){
                count += (restStop.get(i+1)- restStop.get(i)-1) / dist;      // 현재 dist에서 몇 개의 휴게소가 세워질 수 있는지 count에 저장
            }

            // System.out.println("st: " + st + ", en: " + en + ", dist: " + dist + ", count: " + count);

            if(count <= m){ // 세워져야 할 개수와 같거나 적게 세워졌다면
                en = dist - 1; // 거리를 줄인다 // 이분탐색 범위를 앞쪽으로 조정
            } else{ // 많게 세워졌다면
                st = dist + 1; // 거리를 늘린다 // 이분탐색 범위를 뒤쪽으로 조정
            }
        }
        // System.out.println("최종 st: " + st);
        bw.write(String.valueOf(st));
        bw.close(); //close()는 내부적으로 flush()를 자동으로 호출하고 스트림을 닫음
    }
}