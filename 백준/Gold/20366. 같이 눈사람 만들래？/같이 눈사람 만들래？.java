import java.io.*;
import java.util.*;

// 눈덩이 N개 중 서로 다른 4개를 골라서 눈사람을 각각 1개씩, 총 2개를 만들려고 한다.
// 두 눈사람의 키의 차이가 작을수록 두 눈사람의 사이가 좋을 것이라고 믿는다.
public class Main {
    static int n;
    static int[] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
        int[][] pair = new int[n * (n-1) / 2][3];   // 0: 합, 1: 첫 번째 눈덩이 인덱스, 2: 두 번째 눈덩이 인덱스
        int idx = 0;
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                pair[idx][0] = arr[i] + arr[j];
                pair[idx][1] = i;
                pair[idx][2] = j;
                idx++;
            }
        }
        // 합 기준으로 정렬
        Arrays.sort(pair, (a, b) -> Integer.compare(a[0], b[0]));
        int minDiff = Integer.MAX_VALUE;
        // 합 기준으로 정렬되어 있으므로 붙어있는 두 요소끼리만 비교
        for(int i=0; i<pair.length-1; i++){
            int[] temp1 = pair[i];
            int[] temp2 = pair[i+1];
            if(temp1[1] != temp2[1] && temp1[1] != temp2[2] && temp1[2] != temp2[1] && temp1[2] != temp2[2]){
                minDiff = Math.min(minDiff, Math.abs(temp1[0] - temp2[0]));
            }
        }
        System.out.print(minDiff);
    }
}