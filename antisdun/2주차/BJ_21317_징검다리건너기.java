import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_21317_징검다리건너기 {
	
    static int N, K;
    static int[][] jump, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());

        jump = new int[N-1][2];
        dp = new int[N][2];        
        
        for (int i = 0; i < N-1; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
			jump[i][0] = Integer.parseInt(st.nextToken());
			jump[i][1] = Integer.parseInt(st.nextToken());
		}
        
        K = Integer.parseInt(br.readLine());
        
        for (int i = 1; i < N; i++) {
			Arrays.fill(dp[i], 987654321);
		}
        
        dp[0][0] = 0;

        
        for (int i = 1; i < N; i++) {
			dp[i][0] = Math.min(dp[i][0], dp[i-1][0]+jump[i-1][0]);
			
			if (i > 1) {
				dp[i][0] = Math.min(dp[i][0], dp[i-2][0]+jump[i-2][1]);
			}
			
			if (i > 2) {
				dp[i][1] = Math.min(dp[i][1], dp[i-3][0]+K);
				dp[i][1] = Math.min(dp[i][1], dp[i-1][1] + jump[i-1][0]);
				dp[i][1] = Math.min(dp[i][1], dp[i-2][1] + jump[i-2][1]);
			}
			
		}
        
        System.out.println(Math.min(dp[N-1][0], dp[N-1][1]));
        
    }
}
