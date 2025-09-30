import java.io.*;
import java.util.*;

// 시작을 첫 번째 돌에서 시작하지 않아도 된다.
// 오른쪽으로 전진해야 하며, 여러 칸을 전진해도 된다.
// 항상 반대 행으로 이동해야 한다.
// 두루마리의 모든 문자열을 밟아야 한다.
public class Main{
    static int[][][] dp;
    static String str;
    static char[][] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        str = br.readLine();
        String devil = br.readLine();
        String angel = br.readLine();
        arr = new char[2][devil.length()];
        arr[0] = devil.toCharArray();
        arr[1] = angel.toCharArray();

        // 변하는 상태는 어떤 돌다리인지, 두루마리의 몇 번째 문자인지, 밟고 있는 돌이 몇 번째 인지
        dp = new int[2][str.length()][devil.length()];
        for(int i=0; i<2; i++){
            for(int j=0; j<devil.length(); j++){
                // 처음으로 밟을 수 있는 곳을 초기화
                if(arr[i][j] == str.charAt(0)){
                    dp[i][0][j] = 1;
                }
            }
        }

        // 두루마리 문자열에서 문자를 하나씩 고려하면서
        // 각 다리에 대해
        // 돌다리를 순서대로 이동하면서
        for(int i=1; i<str.length(); i++){
            for(int j=0; j<2; j++){
                int nRow = 1-j;     // j가 0이면 1로, 1이면 0으로
                for(int k=0; k<devil.length(); k++){
                    // 두루마리에 적혀있는 문자가 아니면 건너뜀
                    if(arr[j][k] != str.charAt(i)) continue;
                    for(int p=0; p<k; p++){
                        // 이전 위치에서의 경우의 수를 누적
                        dp[j][i][k] += dp[nRow][i-1][p];
                    }
                }
            }
        }

        int rst = 0;
        for(int i=0; i<devil.length(); i++) for(int j=0; j<2; j++) rst += dp[j][str.length()-1][i];
        System.out.print(rst);
    }
}