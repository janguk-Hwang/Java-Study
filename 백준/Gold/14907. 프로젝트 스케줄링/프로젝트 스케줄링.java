import java.io.*;
import java.util.*;

public class Main {
    static int[] indegree, duration, dp;
    static ArrayList<Integer>[] adj;
    static Queue<Integer> q = new LinkedList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        indegree = new int[26];
        adj = new ArrayList[26];
        duration = new int[26];
        dp = new int[26];
        for(int i=0; i<26; i++) adj[i] = new ArrayList<>();
        String line;
        while((line = br.readLine()) != null && !line.isEmpty()){
            String[] token = line.split(" ");
            int task = token[0].charAt(0) - 'A';
            int time = Integer.parseInt(token[1]);
            duration[task] = time;

            if(token.length == 3){
                for(char c : token[2].toCharArray()){
                    int now = c - 'A';
                    adj[now].add(task);
                    indegree[task]++;
                }
            }
        }
        for(int i=0; i<26; i++){
            if(indegree[i] == 0){
                q.add(i);
                dp[i] = duration[i];
            }
        }
        while(!q.isEmpty()){
            int now = q.poll();
            for(int next : adj[now]){
                dp[next] = Math.max(dp[next], dp[now] + duration[next]);
                indegree[next]--;
                if(indegree[next] == 0) q.add(next);
            }
        }

        int result = 0;
        for(int i=0; i<26; i++){
            result = Math.max(result, dp[i]);
        }
        System.out.print(result);
    }
}