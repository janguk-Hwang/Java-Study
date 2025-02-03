import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


//한 배열 A[1], A[2], …, A[n]에 대해서, 부 배열은 A[i], A[i+1], …, A[j-1], A[j] (단, 1 ≤ i ≤ j ≤ n)을 말한다.
//이러한 부 배열의 합은 A[i]+…+A[j]를 의미한다.
public class Main {
    static long T; // 목표 합 T
    static int N, M; // 배열 A와 B의 크기
    static int[] A, B; // 배열 A와 B
    static long answer = 0; // 결과값(쌍의 개수)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        N = Integer.parseInt(br.readLine());
        A = new int[N];
        //입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken()); // 배열 A 입력
        }

        M = Integer.parseInt(br.readLine()); // 배열 B의 크기 입력
        B = new int[M];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken()); // 배열 B 입력
        }

        Map<Long, Long> subA = new HashMap<>(); // A의 부배열 합을 저장하는 맵
        Map<Long, Long> subB = new HashMap<>(); // B의 부배열 합을 저장하는 맵

        // A의 모든 부배열의 합과 그 개수 구하기
        for (int i = 0; i < N; i++) {
            long sum = 0;
            // 부배열은 A[i]부터 A[j]까지의 합
            for (int j = i; j < N; j++) {
                sum += A[j]; // 부배열 합 계산
                Long count = subA.getOrDefault(sum, 0L);    //NullPointerException을 방지하기 위한 기본값
                subA.put(sum, count + 1); // 해당 합의 개수를 저장   ex) sum == 10, count == 1이면 subA.put(10, 2)
            }
        }

        // B의 모든 부배열의 합과 그 개수 구하기
        for (int i = 0; i < M; i++) {
            long sum = 0;
            for (int j = i; j < M; j++) {
                sum += B[j]; // 부배열 합 계산
                Long count = subB.getOrDefault(sum, 0L);
                subB.put(sum, count + 1); // 해당 합의 개수를 저장
            }
        }

        // 위에서 A와 B의 모든 부배열에 대해서 sum과 count를 통해 각 sum에 대한 A와 B의 부배열 개수를 맵에 저장
        // 두 부배열 합을 조합하여 T를 만들 수 있는 경우의 수 계산
        for (Map.Entry<Long, Long> entry : subA.entrySet()) {
            Long aKey = entry.getKey(); // A의 부배열 합
            Long aValue = entry.getValue(); // 해당 합의 개수
            long bKey = T - aKey; // T를 만들기 위한 B의 부배열 합
            Long bValue = subB.getOrDefault(bKey, 0L); // 해당 합의 개수 (없으면 0)
            answer += (aValue * bValue); // 가능한 쌍의 개수 누적 합
        }

        System.out.println(answer);
    }
}