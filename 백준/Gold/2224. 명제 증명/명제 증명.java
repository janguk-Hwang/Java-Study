import java.io.*;
import java.util.*;

public class Main {
    static int n, p, q, rst;
    static boolean[][] dist;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        dist = new boolean[52][52];
        for(int i=0; i<n; i++){
            String[] proposition = br.readLine().split(" => ");
            if(proposition[0].charAt(0) > 96) p = proposition[0].charAt(0) - 'a' + 26;
            else p = proposition[0].charAt(0) - 'A';
            if(proposition[1].charAt(0) > 96) q = proposition[1].charAt(0) - 'a' + 26;
            else q = proposition[1].charAt(0) - 'A';
            dist[p][q] = true;
        }
        for(int k=0; k<52; k++){
            for(int i=0; i<52; i++){
                for(int j=0; j<52; j++){
                    if(dist[i][k] && dist[k][j]){
                        dist[i][j] = true;
                    }
                }
            }
        }

        int cnt = 0;
        for(int i=0; i<52; i++){
            for(int j=0; j<52; j++){
                if(i != j && dist[i][j]) cnt++;
            }
        }

        sb.append(cnt).append("\n");

        for(int i=0; i<52; i++){
            for(int j=0; j<52; j++){
                if(i == j) continue;
                if(dist[i][j]){
                    char from = (i < 26) ? (char)('A' + i) : (char)('a' + (i - 26));
                    char to   = (j < 26) ? (char)('A' + j) : (char)('a' + (j - 26));
                    sb.append(from).append(" => ").append(to).append("\n");
                }
            }
        }
        System.out.print(sb);
    }
}