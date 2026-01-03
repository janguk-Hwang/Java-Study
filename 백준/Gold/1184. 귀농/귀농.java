import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] dr = {-1, 1, 1, -1};
    static int[] dc = {-1, 1, -1, 1};
    static int[][] prefixSum; // 누적합
    static StringTokenizer st;
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        n = Integer.parseInt(br.readLine());
        prefixSum = new int[n + 1][n +1];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++){
                int temp = Integer.parseInt(st.nextToken());
                prefixSum[i][j] = prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1] + temp;
            }
        }
        int rst = 0;
        for(int i=1; i<n; i++){
            for(int j=1; j<n; j++){
                rst += func(i, j, 0, 1);
                rst += func(i, j, 2, 3);
            }
        }
        System.out.println(rst);
    }

    // 교차점 기준 대각선 비교
    static int func(int y, int x, int d1, int d2){
        Map<Integer,Integer> leftMap=new HashMap<>();
        Map<Integer,Integer> rightMap=new HashMap<>();
        if(d1 == 0){
            findRectangle(y, x, d1, leftMap);
            findRectangle(y+1, x+1, d2, rightMap);
        }
        else{
            findRectangle(y+1, x, d1, leftMap);
            findRectangle(y,x+1, d2, rightMap);
        }
        int cnt = 0;
        for(int sum : leftMap.keySet()){
            if(rightMap.containsKey(sum)) cnt += leftMap.get(sum)*rightMap.get(sum);
        }
        return cnt;
    }

    static void findRectangle(int nr, int nc, int dir, Map<Integer,Integer> map){
        int tempR = nr;
        while(tempR>0 && tempR<=n){
            int tempC = nc;
            while(tempC >0 && tempC <=n){
                int areaSum;
                // 누적합으로 빠르게 수익 얻어서 map에 합과 해당 직사각형의 개수를 저장
                if(dir == 0) areaSum = getArea(tempR, tempC, nr, nc);
                else if(dir == 1) areaSum = getArea(nr, nc, tempR, tempC);
                else if(dir == 2) areaSum = getArea(nr, tempC, tempR, nc);
                else areaSum = getArea(tempR, nc, nr, tempC);
                map.put(areaSum, map.getOrDefault(areaSum, 0) + 1);
                tempC += dc[dir];
            }
            tempR += dr[dir];
        }
    }

    static int getArea(int r1,int c1,int r2,int c2){
        return prefixSum[r2][c2] - prefixSum[r1-1][c2] - prefixSum[r2][c1-1] + prefixSum[r1-1][c1-1];
    }
}