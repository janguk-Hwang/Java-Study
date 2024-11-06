import java.io.*;
import java.util.PriorityQueue;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int absA = Math.abs(a);
            int absB = Math.abs(b);
            if (absA == absB) {
                return Integer.compare(a, b);
            }
            return Integer.compare(absA, absB);
        });

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());

            if (input == 0) {
                if (!pq.isEmpty()) {
                    bw.write(pq.poll() + "\n");
                } else {
                    bw.write("0\n");
                }
            } else {
                pq.add(input);
            }
        }
        bw.flush();
        bw.close();
    }
}