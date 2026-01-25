import java.io.*;
import java.util.*;

// 맨 위 행을 지우면 지울수록 동일한 문자열이 발생할 확률은 단조적으로 증가한다.
// 문자열의 길이가 길어지면 중복이 아닐 확률쪽으로만 증가한다.
// 매개변수 탐색으로 풀이 가능
// 결정 문제: mid개의 행을 위에서 제거했을 때, 중복이 발생하지 않는가?
public class Main {
    static int r, c;
    static String[] arr;
    static Set<String> set;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new String[r];
        for(int i=0; i<r; i++) arr[i] = br.readLine();
        // ttttttffffff
        int start = 0; int end = r;

        set = new HashSet<>();
        boolean flag = false;
        int mid = start;
        for(int i=0; i<c; i++){
            StringBuilder sb = new StringBuilder();
            for(int j=mid; j<r; j++) sb.append(arr[j].charAt(i));
            if(set.contains(sb.toString())) {
                flag = true;
                break;
            }
            set.add(sb.toString());
        }
        if(flag){
            System.out.print(0);
            return;
        }

        while(start + 1 < end){
            set = new HashSet<>();
            mid = (start + end) / 2;
            flag = false;
            for(int i=0; i<c; i++){
                StringBuilder sb = new StringBuilder();
                // mid부터 맨 밑까지의 문자열
                for(int j=mid; j<r; j++) sb.append(arr[j].charAt(i));
                if(set.contains(sb.toString())){
                    flag = true;
                    break;
                }
                set.add(sb.toString());
            }
            // 중복 발생 -> mid를 위쪽으로 이동시키기 위해 end를 mid로 당김
            if(flag) end = mid;
            else start = mid;
        }
        System.out.print(start);
    }
}