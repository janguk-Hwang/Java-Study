import java.io.*;
import java.util.*;

// 4 4 8 크기의 상자가 있다.
// 이 상자는 4 4 4, 4 4 4 크기의 상자로 나눌 수 있다.
// 큐브는 2의 제곱꼴의 길이를 가진다.
// 상자를 채울 수 있는 가장 큰 2의 제곱꼴로 채우고 남은 공간을 가능한 큰 큐브부터 채워나간다.
// 10 10 11의 상자가 있으면 8 8 8 큐브로 채우고 나머지는 2 2 2, 1 1 1 큐브로 채운다.
// 반례가 존재하는가? 어차피 큐브는 제곱꼴로 만들 수 있기 때문에 16 16 16을 8 8 8 4개로 채우는 것은 항상 최적해가 될 수 없다.
// 가능한 가장 큰 큐브로 채우는 것과 그보다 작은 큐브로 나눠서 채우는 것은 사용한 큐브의 수만 늘어날 뿐이다.
import java.io.*;
import java.util.*;
public class Main {
    static int length, width, height;
    static int n, rst;
    static int[] cubeCnt = new int[20];   // Bi
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        length = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(br.readLine());
        int maxCube = 0;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            cubeCnt[x] = Integer.parseInt(st.nextToken());
            if(x > maxCube) maxCube = x;
        }
        rst = 0;
        long alreadyFilled = 0L;
        for(int i=maxCube; i>=0; i--){
            int cubeLen = 1 << i;
            // 각 변마다 현재 크기의 큐브가 들어갈 수 있는 개수로 현재 턴에서 사용가능한 큐브 개수 계산
            int nowTurnPossibleNum = (length / cubeLen) * (width / cubeLen) * (height / cubeLen);
            alreadyFilled = alreadyFilled << 3;
            nowTurnPossibleNum -= (int) alreadyFilled;
            int nowTurnUse = Math.min(nowTurnPossibleNum, cubeCnt[i]);
            rst += nowTurnUse;
            alreadyFilled += nowTurnUse;
        }
        if(alreadyFilled == (long) length * width * height) System.out.print(rst);
        else System.out.print(-1);
    }
}