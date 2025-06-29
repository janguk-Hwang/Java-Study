import java.io.*;
import java.util.*;

// 그룹의 합 중 최소값이 최대한 크게 해야한다.
// 시험지를 정렬할 수는 없다.
// 앞에서부터 mid값보다 크도록 점수를 누적. 끝까지 진행해서 그룹의 수가 몇 개인지 확인
// 그룹의 수가 k보다 같거나 많으면 mid값을 더 높여서 그룹의 최소값을 늘린다.
public class Main {
    static int[] arr;
    static int n, k;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        int totalPoint = 0;
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            totalPoint += arr[i];
        }
        // 결정 문제: mid값을 그룹의 점수 하한선으로 설정했을 때 만들어지는 그룹의 수가 k보다 크거나 같은가?
        // mid down -> k up      mid up -> k down
        // k보다 크거나 같으면 true, 작으면 false 반환
        // tttttttttttttttfffffffffffffffff
        // 시험지를 mid만큼씩 잘라서 그룹을 만들다가 mid만큼 만들 수 없어서 버려진 시험지들은?
        // mid에 도달하지 못했으므로 그룹이 되지 않는다.
        // 하지만 mid가 조정되면서 남는 시험지 없이 모두 사용될 것이다.
        // 남는 시험지가 있다면 가장 합이 낮은 그룹에 넣으면 mid를 올릴 수 있기 때문이다.
        int start = 0; int end = totalPoint + 1;
        while(start + 1 < end){
            int mid = (start + end) / 2;
            int sum = 0;
            int cnt = 0;
            for(int i=0; i<n; i++){
                sum += arr[i];
                if(sum >= mid){
                    cnt++;
                    sum = 0;
                }
            }
            if(cnt >= k) start = mid;
            else end = mid;
        }
        System.out.print(start);
    }
}