import java.io.*;
import java.util.*;

// X: 마을의 위치, A: 마을에 사는 사람의 수
// 각 마을까지의 거리의 합이 아니라, 각 사람까지의 거리의 합임에 유의한다
// 위치, 사람의 수의 곱을 총 사람의 수로 나누는 것인가? 예제는 22 / 11 = 2로 맞긴하다.
// 1위치에 1명, 10위치에 2명일 때, 우체국을 7에 세우면 총 합은 12, 10에 세우면 9이다.
// 그래서 이 방법은 틀렸다.
// 그러면 어떻게 해야 할까?
// 절반 이상의 사람이 많은 곳에 세우면? 딱 절반이어야 할까? 아니면 절반보다 커야 할까?
// 1       5
// 49      51
public class Main {
    static int n;
    static int[][] arr;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException{
        n = Integer.parseInt(br.readLine());
        arr = new int[n][2];
        long totalPeople = 0L;
        long aSum = 0L;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());       // X - 마을의 위치
            arr[i][1] = Integer.parseInt(st.nextToken());       // A - 마을에 사는 사람의 수
            totalPeople += arr[i][1];
        }
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));
        for(int i=0; i<n; i++){
            aSum += arr[i][1];
            // i번째에 우체국을 세운다.
            if(aSum >= (totalPeople + 1) / 2){
                System.out.print(arr[i][0]);
                return;
            }
        }
    }
}