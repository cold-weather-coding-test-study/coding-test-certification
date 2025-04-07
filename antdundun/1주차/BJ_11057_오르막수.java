import java.io.BufferedReader;
import java.io.InputStreamReader;
public class BJ_11057_오르막수 {

    static int N;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        dp = new long[N+1][10];
        
        for (int i = 0; i < 10; i++) {
        	dp[1][i] = 1;
		}
        
        for (int i = 2; i < N+1; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = j; k < 10; k++) {
					dp[i][j] += dp[i-1][k] % 10007;
				}
			}
		}
        
        int ans = 0;
        for (int i = 0; i < 10; i++) {
			ans += dp[N][i];
		}
        
        System.out.println(ans%10007);
        
    }

}
