import java.io.*;
import java.util.StringTokenizer;

// 모든 부분에서 "그런거겠지, 맞겠지 뭐"같은 생각으로 넘어가지 않고 실제로 어떻게 진행이 되는 거고 어떻게 작동이 되는 건지 직접해보고 검증하기
// 이분 탐색의 아이디어는 경계를 포함하는 구간 [lo, hi]을 잡은 뒤 구간의 길이를 절반씩 줄여나가며 lo, hi이 경계 지점에 위치하도록 하는 것
public class Main {
    static int n, m;
    static int[] tree;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        tree = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<n; i++){
            tree[i] = Integer.parseInt(st.nextToken());
        }
        // 이 알고리즘이 예외없이 모든 상황에 대해 해결할 수 있는가?
        // 시작부터 mid값이 최종 lo, hi 중 하나라면?
        int start = 0;
        int end = 1_000_000_000;
        while(start + 1 < end){ // start와 end가 연속인 상태이면 end < end와 같으므로 종료
            long sum = 0;   // 1백만개의 나무가 있고 h를 0으로 설정했을때 10^9 * 10^6로 10^15를 담을 수 있게 long으로 선언
            int mid = (start + end) / 2;  // 절단기에 지정한 높이
            for(int i=0; i<n; i++){
                // if문이 없으면 음수를 더해서 제대로 된 답을 구할 수 없다.
                if(tree[i] > mid) sum += tree[i] - mid;
            }
            // start(0)은 true를 반환, end(10^9)은 false를 반환 0과 10^9 사이 어딘가에서 값이 바뀌는 부분이 있다. 그 부분의 앞에 start를 뒤에 end를 두는 것이 목표
            // '='가 붙어야 하는지 아닌지는 '='인 경우가 '>'일때와 같은 bool값인 같은지 고려해야 한다. '<'인 경우와 bool값이 같으면 '='를 빼면 된다.
            if(sum >= m){   // mid값으로 잘랐을 때 true를 반환하면 start를 mid로 바꾼다. start, end가 경계 지점에 위치하도록 하기 위해서
                start = mid;
            }
            // mid값으로 잘랐을 때 false를 반환했을 때는 end를 mid로 바꾼다.
            else end = mid;
        }

        // start(0)이 true를 반환했었으니까 while문을 다 수행한 후 start쪽이 true를 가지고 있다.
        System.out.println(start);
        // 만약 문제에서 m만큼의 나무를 가져갈 수 없을 때의 절단기의 높이의 최솟값을 구하라고 했다면 end를 출력하면 된다.
    }
}