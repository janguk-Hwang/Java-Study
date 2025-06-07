import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] arr;
    static int[] pre = new int[1_000_001];
    static int[] next = new int[1_000_001];
    static boolean[] isPresent = new boolean[1_000_001];
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            isPresent[arr[i]] = true;
        }
        for(int i=0; i<n; i++){
            int now = arr[i];
            int nxt = arr[(i+1) % n];
            int pr = arr[(i+n-1)%n];
            next[now] = nxt;
            pre[now] = pr;
        }
        for(int k=0; k<m; k++){
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            if(str.equals("BN")){
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                sb.append(next[i]).append("\n");
                if(!isPresent[j]){
                    int temp = next[i];
                    next[i] = j;
                    pre[j] = i;
                    next[j] = temp;
                    //pre[next[i]] = j;
                    pre[temp] = j;
                    isPresent[j] = true;
                }
                continue;
            }
            if(str.equals("BP")){
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                sb.append(pre[i]).append("\n");
                if(!isPresent[j]){
                    next[pre[i]] = j;
                    pre[j] = pre[i];
                    next[j] = i;
                    pre[i] = j;
                    isPresent[j] = true;
                }
                continue;
            }
            if(str.equals("CN")){
                int i = Integer.parseInt(st.nextToken());
                sb.append(next[i]).append("\n");
                int temp = next[i];
                next[i] = next[temp];
                pre[next[temp]] = i;
                isPresent[temp] = false;
            }
            if(str.equals("CP")){
                int i = Integer.parseInt(st.nextToken());
                sb.append(pre[i]).append("\n");
                int temp = pre[i];
                int prepre = pre[temp];
                pre[i] = prepre;
                next[prepre] = i;
                isPresent[temp] = false;
            }
        }
        System.out.print(sb);
    }
}