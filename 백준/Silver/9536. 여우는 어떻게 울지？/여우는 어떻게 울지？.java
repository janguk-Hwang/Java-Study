import java.util.*;
import java.io.*;

public class Main {
    static int t;
    static Set<String> sound;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            String[] input = br.readLine().split(" ");
            sound = new HashSet<>();
            String str;
            while (!(str = br.readLine()).equals("what does the fox say?")) {
                String[] token = str.split(" ");
                sound.add(token[2]);
            }
            for(String s : input){
                if(!sound.contains(s)){
                    sb.append(s).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
