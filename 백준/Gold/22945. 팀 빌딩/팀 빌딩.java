import java.io.*;
import java.util.*;

// 두 포인터를 각각 양 끝에 두고 능력치가 작은 쪽을 안으로 이동시키면서 결과값 계산
public class Main {
    static int n, rst;
    static int[] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
        int left = 0; int right = n - 1; rst = 0;
        // 개발자는 적어도 2명이 있어야 하므로 left < right, 같으면 안 됨
        while(left < right){
            int between = right - left - 1;
            int ability = between * Math.min(arr[left], arr[right]);
            rst = Math.max(rst, ability);
            if(arr[left] < arr[right]) left++;
            else right--;
        }
        System.out.println(rst);
    }
}