import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1495_기타리스트 {
	
    static int N, S, M;
    static int[] V;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        V = new int[N+1];
        dp = new int[N+1][M+1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++) {
			V[i] = Integer.parseInt(st.nextToken());
		}
        
        for (int i = 0; i < N+1; i++) {
			Arrays.fill(dp[i], -1);
		}
        
        dp[0][S] = 0;
        
        for (int i = 1; i < N+1; i++) {        	
			for (int j = 0; j < M+1; j++) {
				if (dp[i-1][j] >= 0) {
					int up = j+V[i];
					int down = j-V[i];
					
					if (up <= M) dp[i][up] = i;
					if (down >= 0) dp[i][down] = i;
				}
			}
		}
        
        for (int i = M; i >= 0; i--) {
        	if (dp[N][i] >= 0) {
				System.out.println(i);
				return;
			}
		}
        
        System.out.println(-1);
        
    }
}
