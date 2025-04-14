import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_10844_쉬운계단수 {
	
	static int N;
	static long[][] dp;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        dp = new long[N+1][11];
        
        for (int i = 0; i < 10; i++) {
        	dp[1][i] = 1;
		}
        
        for (int i = 2; i < N+1; i++) {
			for (int j = 0; j < 10; j++) {
				if (j-1 >= 0) dp[i][j] += dp[i-1][j-1];
				if (j+1 <= 9) dp[i][j] += dp[i-1][j+1];
				dp[i][j] %= 1000000000;
			}
		}
        
        
        long ans = 0;
        for (int i = 1; i < 10; i++) {
			ans += dp[N][i];
		}
        
        System.out.println(ans%1000000000);
    }

}
