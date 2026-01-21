import java.io.*;
import java.util.*;

// 몇 점이 있어야 최적화해서 적어도 k명을 이길 수 있는가?
// 무조건 각 병사의 힘, 민첩, 지능의 합을 구하고 정렬하고 k번째 병사의 힘, 민, 지 합을 구하면 되는 건 아니다.
// 100 0 0
// 0 100 0
// 60 60 0
// k는 2명 최소의 스탯 포인트는 100이 아니라 200이다.
public class Main {
    static int n, k, maxS, maxM, maxJ;
    static int[][] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n][3];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
            maxS = Math.max(maxS, arr[i][0]);
            maxM = Math.max(maxM, arr[i][1]);
            maxJ = Math.max(maxJ, arr[i][2]);
        }
        int start = -1; int end = maxS + maxM + maxJ;
        // fffffffttttttt
        while(start + 1 < end){
            int mid = (start + end) / 2;
            if(isPossible(mid)) end = mid;
            else start = mid;
        }
        System.out.print(end);
    }

    static boolean isPossible(int mid){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                int target = mid - arr[i][0] - arr[j][1];
                if(target < 0) continue;
                int canWinCnt = 0;
                for(int p=0; p<n; p++){
                    if(arr[p][0] <= arr[i][0] && arr[p][1] <= arr[j][1] && arr[p][2] <= target){
                        canWinCnt++;
                        if(canWinCnt >= k) return true;
                    }
                }
            }
        }
        return false;
    }
}