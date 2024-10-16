import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            HashMap<Integer, Integer> count = new HashMap<>();

            int N = Integer.parseInt(br.readLine());
            for (int j = 0; j < N; j++) {
                String[] input = br.readLine().split(" ");
                int number = Integer.parseInt(input[1]);

                if (input[0].equals("I")) {
                    minHeap.add(number);
                    maxHeap.add(number);
                    count.put(number, count.getOrDefault(number, 0) + 1);
                } else if (input[0].equals("D")) {
                    if (minHeap.isEmpty() && maxHeap.isEmpty()) {
                        continue;
                    }
                    if (number == 1) {
                        delete(maxHeap, count);
                    } else if (number == -1) {
                        delete(minHeap, count);
                    }
                }
            }
            
            while (!minHeap.isEmpty() && !count.containsKey(minHeap.peek())) {
                minHeap.poll();
            }
            while (!maxHeap.isEmpty() && !count.containsKey(maxHeap.peek())) {
                maxHeap.poll();
            }

            if (minHeap.isEmpty() || maxHeap.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                System.out.println(maxHeap.peek() + " " + minHeap.peek());
            }
        }
    }

    public static void delete(PriorityQueue<Integer> heap, HashMap<Integer, Integer> count) {
        while (!heap.isEmpty()) {
            int value = heap.poll();
            if (count.containsKey(value) && count.get(value) > 0) {
                count.put(value, count.get(value) - 1);
                if (count.get(value) == 0) {
                    count.remove(value);
                }
                break;
            }
        }
    }
}
