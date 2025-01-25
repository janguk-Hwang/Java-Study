import java.util.*;
import java.io.*;

//문제 난이도의 합은 L보다 크거나 같고, R보다 작거나 같아야 한다.
//가장 어려운 문제와 가장 쉬운 문제의 난이도 차이는 X보다 크거나 같아야 한다.
//캠프에 사용할 문제를 고르는 방법의 수를 구해보자.
public class Main {
    static int[] Ai;
    static int N,L,R,X;
    static int result;
    public static void main(String[] args)throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());   //가지고 있는 문제의 개수
    L = Integer.parseInt(st.nextToken());   //상한 문제 난이도 합
    R = Integer.parseInt(st.nextToken());   //하한 문제 난이도 합
    X = Integer.parseInt(st.nextToken());   //문제간의 상한 난이도 차이

    String[] input = br.readLine().split(" ");
    Ai = new int[N];
    for(int i = 0; i < N; i++) {
        Ai[i] = Integer.parseInt(input[i]);
    }

    Arrays.sort(Ai);

    func(0, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
    System.out.println(result);
    
    }

    
    
    public static void func(int idx, int cnt, int sum, int max, int min) {
        //문제가 2개 이상이고, L,R,X 조건이 다 맞으면 result 증가
        if (cnt >= 2) {
            if (sum >= L && sum <= R && max - min >= X) {
                result += 1;
            }
        }

        //idx, cnt는 증가시키고 sum, max, min은 갱신하면서 재귀함수 호출
        for (int i = idx; i < N; i++) {
            func(i + 1, cnt + 1, sum + Ai[i], Math.max(max, Ai[i]), Math.min(min, Ai[i]));
        }
    }
}