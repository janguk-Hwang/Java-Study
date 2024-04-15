import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int K = sc.nextInt();
        
        int[] put = new int[10000];
        int j = 1;
        put[0] = 0;
        
        for(int i=1; i<=N; i++){
            if((N%i) == 0){
                put[j] = i;
                j++;
            }
        }
        if(j<K){
            System.out.println("0");
        }
        else {
            System.out.println(put[K]);
        }
        sc.close();
    }
}