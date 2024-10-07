import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        System.out.println(findSister(N, K));
    }

    static int findSister(int N, int K) {
        if(N == K){
            return 0;
        }

        if (N >= K) {
            return N - K; //걸어서 이동
        }

        int maxPosition = 100000;
        int[] time = new int[maxPosition + 1];
        boolean[] visited = new boolean[maxPosition + 1];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(N); // 수빈이의 위치로부터 시작
        visited[N] = true;
        time[N] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // 동생을 찾으면 탐색 종료
            if (current == K) {
                return time[current];
            }

            // 순간이동 (current * 2)
            if (current * 2 <= maxPosition && !visited[current * 2]) {
                queue.add(current * 2);
                visited[current * 2] = true;
                time[current * 2] = time[current]; // 순간이동은 시간 추가 없음
            }
                        
            //뒤로 걷기(-1)
            if (current - 1 >= 0 && !visited[current - 1]) {
                queue.add(current - 1);
                visited[current - 1] = true;
                time[current - 1] = time[current] + 1; //1초 추가
            }

            //앞으로 걷기(+1)
            if (current + 1 <= maxPosition && !visited[current + 1]) {
                queue.add(current + 1);
                visited[current + 1] = true;
                time[current + 1] = time[current] + 1; //1초 추가
            }
        }

        return -1;  //비정상 종료
    }
}