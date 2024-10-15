import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());  // 테스트 케이스 수
        for (int i = 0; i < T; i++) {
            int k = Integer.parseInt(br.readLine());  // 연산의 수

            // 최소 힙과 최대 힙
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            HashMap<Integer, Integer> countMap = new HashMap<>();  // 값의 개수를 관리하는 HashMap

            for (int j = 0; j < k; j++) {
                String[] input = br.readLine().split(" ");
                String operation = input[0];
                int num = Integer.parseInt(input[1]);

                if (operation.equals("I")) {
                    // 삽입 연산
                    minHeap.add(num);
                    maxHeap.add(num);
                    countMap.put(num, countMap.getOrDefault(num, 0) + 1);
                } else if (operation.equals("D")) {
                    if (num == 1) {
                        // 최댓값 삭제
                        deleteTop(maxHeap, countMap);
                    } else if (num == -1) {
                        // 최솟값 삭제
                        deleteTop(minHeap, countMap);
                    }
                }
            }

            // 연산이 끝난 후 힙 정리 (유효하지 않은 값들 제거)
            cleanup(minHeap, countMap);
            cleanup(maxHeap, countMap);

            // 결과 출력
            if (minHeap.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                System.out.println(maxHeap.peek() + " " + minHeap.peek());
            }
        }
    }

    // 우선순위 큐에서 최댓값이나 최솟값을 삭제하는 함수
    private static void deleteTop(PriorityQueue<Integer> heap, HashMap<Integer, Integer> countMap) {
        while (!heap.isEmpty()) {
            int value = heap.poll();
            if (countMap.containsKey(value) && countMap.get(value) > 0) {
                // 삭제할 값의 카운트를 줄임
                countMap.put(value, countMap.get(value) - 1);
                if (countMap.get(value) == 0) {
                    countMap.remove(value);
                }
                break;
            }
        }
    }

    // 힙에서 유효하지 않은 값을 제거하는 함수
    private static void cleanup(PriorityQueue<Integer> heap, HashMap<Integer, Integer> countMap) {
        while (!heap.isEmpty() && (!countMap.containsKey(heap.peek()) || countMap.get(heap.peek()) == 0)) {
            heap.poll();
        }
    }
}
