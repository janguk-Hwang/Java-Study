import java.util.*;

public class Main {
	static int[] dp;
    public static void main(String[] args){
    	Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        dp = new int[1001];
        
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        /*dp[3] = 3;
        dp[4] = 5;
        dp[5] = 8;
        dp[6] = 13;*/
        
        System.out.println(dp(n));
    }
    
    public static int dp(int i) {
		
    	if(dp[i] > 0) return dp[i];
    	
    	dp[i] = dp(i-1) + dp(i-2);	//dp[i]는 dp[i-1]와 dp[i-2]를 더한 값
    	return dp[i]%10007;
    }
}
