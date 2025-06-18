import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Deque<Integer>> list = new ArrayList<>();
    static int n;
    static int[] arr;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(br.readLine());
        int offset = 1000;
        int[] next = new int[2001];
        int[] prev = new int[2001];
        Arrays.fill(next, Integer.MAX_VALUE);
        Arrays.fill(prev, Integer.MAX_VALUE);
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        for (int i = 0; i < sorted.length; i++) {
            if (i + 1 < sorted.length) {
                next[sorted[i] + offset] = sorted[i + 1];
            }
            if (i - 1 >= 0) {
                prev[sorted[i] + offset] = sorted[i - 1];
            }
        }
        for (int num : arr) {
            boolean flag = false;
            for (Deque<Integer> deque : list) {
                if (!deque.isEmpty()) {
                    if (deque.peekFirst() == next[num + offset]) {
                        deque.offerFirst(num);
                        flag = true;
                        break;
                    } else if (deque.peekLast() == prev[num + offset]) {
                        deque.offerLast(num);
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                Deque<Integer> newDeque = new LinkedList<>();
                newDeque.offer(num);
                list.add(newDeque);
            }
        }
        System.out.println(list.size());
    }
}