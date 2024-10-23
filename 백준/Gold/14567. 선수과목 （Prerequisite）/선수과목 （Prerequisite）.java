import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] in_degree;
    static ArrayList<ArrayList<Integer>> graph;//과목 간의 선수과목 관계를 저장할 그래프 - ArrayList안에 ArrayList가 있어서 선수과목 관계 저장 가능
    static int[] semester; //최소 학기 수 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        in_degree = new int[N + 1];
        semester = new int[N + 1];
        graph = new ArrayList<>();

        //그래프 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<Integer>());
        }

        //선행관계 입력
        for (int i = 0; i < M; i++) {
            String[] in = br.readLine().split(" ");
            int A = Integer.parseInt(in[0]);
            int B = Integer.parseInt(in[1]);
            //A 과목을 들어야 B 과목을 들을 수 있음
            graph.get(A).add(B);
            in_degree[B]++; //B 과목의 진입차수를 증가시킴(B과목의 선수과목의 개수 증가)
        }

        //진입차수가 0인 과목을 큐에 추가
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (in_degree[i] == 0) {
                q.offer(i);
                semester[i] = 1; //진입차수가 0인 과목은 1학기에 들을 수 있음
            }
        }

        //위상 정렬을 이용한 최소 학기 계산
        while (!q.isEmpty()) {
            int current_lecture = q.poll(); //현재 수강할 수 있는 과목

            //현재 과목을 들은 후 수강할 수 있는 다음 과목들 처리
            //선수과목의 수 만큼 반복하면서 진입차수 감소, 최소 학기 갱신
            for (int next : graph.get(current_lecture)) {
                //current_lecture 과목을 이수했으니 next과목의 진입차수 감소
                in_degree[next]--;
                //어떤 과목들은 선수과목이 있어 해당되는 모든 과목을 먼저 이수해야만 해당 과목을 이수할 수 있게 되어 있다.
                //만약 next가 선수과목을 여러개 가지고 있을 경우 모든 선수과목을 이수해야 하기 때문에 max()를 통해 가장 큰 최소 학기로 갱신
                semester[next] = Math.max(semester[next], semester[current_lecture] + 1); //최소 학기 갱신

                if (in_degree[next] == 0) { //진입차수가 0이 된 과목을 큐에 추가해서 또 반복
                    q.offer(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(semester[i]).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}