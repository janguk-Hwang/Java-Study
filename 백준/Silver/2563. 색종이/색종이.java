import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int sum = 0;
        int X,Y = 0;
        int[][] paper = new int[100][100];
        //모두 0으로 초기화
        for(int i=0; i<100; i++){
            for(int j=0; j<100; j++){
                paper[i][j] = 0;
            }
        }
        for(int i=0; i<N; i++){
            X = sc.nextInt();
            Y = sc.nextInt();
            for(int k=Y; k<(Y+10); k++){
                for(int j=X; j<(X+10); j++){
                    paper[k][j] = 1;
                }
            }
        }
        for(int i=0; i<100; i++){
            for(int j=0; j<100; j++){
                if(paper[i][j] == 1){
                    sum++;
                }
            }
        }
        System.out.println(sum);
    }
}