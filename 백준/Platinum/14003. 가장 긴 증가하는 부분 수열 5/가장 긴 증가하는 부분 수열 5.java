import java.io.*;
import java.util.*;

public class Main {
    static int n, pos;
    static int[] arr, prev, listIdx, listValue;
    static ArrayList<Integer> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        prev = new int[n];
        listIdx = new int[n];
        listValue = new int[n];
        Arrays.fill(prev, -1);
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
        for(int i=0; i<n; i++){
            pos = Collections.binarySearch(list, arr[i]);
            if(pos < 0) pos = -(pos + 1);
            // listValue는 list의 각 길이 위치에서 마지막 원소가 원래 배열에서 몇 번째 인덱스인지 저장
            // 즉, 길이 pos+1인 LIS의 마지막 원소는 arr[lisVal[pos]] 이다.
            if(pos == list.size()){
                list.add(arr[i]);
                listValue[pos] = i;
            }
            else{
                list.set(pos, arr[i]);
                listValue[pos] = i;
            }
            // 각 원소가 lis에서 몇 번째 위치에 들어가는지 저장
            listIdx[i] = pos;
            if(pos > 0) prev[i] = listValue[pos-1];
        }
        int len = list.size();
        sb.append(len).append("\n");
        ArrayList<Integer> result = new ArrayList<>();
        int cur = -1;
        for(int i=n-1; i>=0; i--){
            if(listIdx[i] == len-1){
                cur = i; break;
            }
        }
        while(cur!=-1){
            result.add(arr[cur]);
            cur = prev[cur];
        }
        Collections.reverse(result);
        for(int i : result) sb.append(i).append(" ");
        System.out.print(sb);
    }
}