import java.util.*;
import java.io.*;

public class Main {
    static long[] segTree;
    static int n, m;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int size = treeSize();
        segTree = new long[size];
        int startIndex = size / 2;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            long value = Long.parseLong(st.nextToken());
            segTree[startIndex + i] = value;
        }

        init(startIndex);

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            long a = Long.parseLong(st.nextToken()) + startIndex - 1;
            long b = Long.parseLong(st.nextToken()) + startIndex - 1;
            sb.append(query(a, b)).append("\n");
        }
        System.out.println(sb);
    }

    public static void init(int startIndex) {
        for(int i = startIndex; i< startIndex +n; i++){
            int nidx = i;
            while(nidx > 1){
                nidx /= 2;
                segTree[nidx] = Math.min(segTree[nidx * 2], segTree[nidx * 2 + 1]);
            }
        }
    }

    public static long query(long startIndex, long endIndex){
        long min = Long.MAX_VALUE;

        while(startIndex <= endIndex){
            if(startIndex % 2 == 1){
                min = Math.min(min, segTree[(int) startIndex]);
            }
            if(endIndex % 2 == 0){
                min = Math.min(min, segTree[(int) endIndex]);
            }

            startIndex = (startIndex + 1) / 2;
            endIndex = (endIndex - 1) / 2;
        }
        return min;
    }

    public static int treeSize() {
        int i = 0;
        while(true){
            if(Math.pow(2, i++) >= n){
                return (int)Math.pow(2, i);
            }
        }
    }
}
