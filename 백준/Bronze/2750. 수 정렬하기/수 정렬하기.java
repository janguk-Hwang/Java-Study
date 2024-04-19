import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int[] input = new int[N];
        int in = 0;
        
        for(int i=0; i<N; i++){
            in = sc.nextInt();
            input[i] = in;
        }
        Arrays.sort(input);
        for(int j=0; j<N; j++){
            System.out.println(input[j]);
        }
    }
}