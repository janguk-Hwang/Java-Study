import java.io.*;
import java.util.*;

public class Main{
    static BitSet bitSet;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args)throws Exception{
        st = new StringTokenizer(br.readLine());
        bitSet = new BitSet(33_554_432);
        while(st.hasMoreTokens()){
            int num = Integer.parseInt(st.nextToken());
            if(!bitSet.get(num)){
                bitSet.set(num);
                sb.append(num).append(" ");
            }
        }
        System.out.print(sb);
    }
}