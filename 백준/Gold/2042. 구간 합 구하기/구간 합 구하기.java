import java.io.*;
import java.util.*;

public class Main {
    static long[] tree;
    static long[] arr;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken()); // 수의 개수
        int M = Integer.parseInt(st.nextToken()); // 수의 변경 횟수
        int K = Integer.parseInt(st.nextToken()); // 구간 합을 구하는 횟수

        arr = new long[N + 1];
        tree = new long[N * 4]; // 세그먼트 트리 배열 (4배 크기로 선언)

        // 입력값 배열 저장
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        
        // 세그먼트 트리 초기화
        init(1, N, 1);

        // 명령 처리
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) { // 값 변경 연산
                update(1, N, 1, b, c - arr[b]);
                arr[b] = c; // 값 업데이트
            } else if (a == 2) { // 구간 합 연산
                System.out.println(sum(1, N, 1, b, (int) c));
            }
        }
    }

    // 세그먼트 트리 초기화 함수
    static long init(int start, int end, int node) {
        if (start == end) return tree[node] = arr[start];
        
        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    // 특정 원소 값을 변경하는 함수 (구간 업데이트)
    static void update(int start, int end, int node, int index, long diff) {
        if (index < start || index > end) return; // 범위 밖이면 리턴
        
        tree[node] += diff; // 차이만큼 갱신
        
        if (start != end) { // 리프 노드가 아니면 자식 노드도 갱신
            int mid = (start + end) / 2;
            update(start, mid, node * 2, index, diff);
            update(mid + 1, end, node * 2 + 1, index, diff);
        }
    }

    // 특정 구간의 합을 구하는 함수
    static long sum(int start, int end, int node, int left, int right) {
        if (left > end || right < start) return 0; // 범위를 벗어난 경우
        if (left <= start && end <= right) return tree[node]; // 구간 내에 포함된 경우
        
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }
}
