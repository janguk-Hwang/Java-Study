import java.io.*;
import java.util.*;

public class Main {
    static int t;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            int n = Integer.parseInt(br.readLine());
            String[] pn = new String[n];
            for(int i=0; i<n; i++){
                pn[i] = br.readLine();
            }
            Arrays.sort(pn);
            String str1 = pn[0];
            boolean flag = false;
            outer:
            for(int i=1; i<n; i++){
                String str2 = pn[i];
                int size = Math.min(str1.length(), str2.length());
                int cnt = 0;
                for(int j=0; j<size; j++){
                    if(str1.charAt(j) == str2.charAt(j)){
                        cnt++;
                        if(cnt == size){
                            sb.append("NO").append("\n");
                            flag = true;
                            break outer;
                        }
                    }
                }
                str1 = str2;
            }
            if(!flag) sb.append("YES").append("\n");
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }
}