import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] student = new int[30];
        int[] result = new int[2];
        for(int i=0; i<28; i++){
            int num = sc.nextInt() - 1;
            student[num] = 1;
        }
        for(int j=0; j<30; j++){
            if(student[j]==0){
                System.out.println((j+1));
            }
        }
    }
}