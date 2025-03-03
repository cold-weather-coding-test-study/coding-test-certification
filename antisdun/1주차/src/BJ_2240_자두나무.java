import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class BJ_2240_자두나무 {

    static int T, W;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        dp = new int[T+1][W+1];

        for (int i = 1; i < T+1; i++) {
            int fall = Integer.parseInt(br.readLine());

            for (int j = 0; j < W+1; j++) {
                // 시작
                if (j==0) {
                    if (fall==1) {
                        dp[i][j] = dp[i-1][j]+1;
                    } else {
                        dp[i][j] = dp[i-1][j];
                    }

                    continue;
                }

                // 1번 나무
                if (j%2==0) {
                    if (fall==1) {
                        dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]+1);
                    } else {
                        dp[i][j] = Math.max(dp[i-1][j-1]+1, dp[i-1][j]);
                    }
                } else { // 2번 나무
                    if (fall==2) {
                        dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]+1);
                    } else {
                        dp[i][j] = Math.max(dp[i-1][j-1]+1, dp[i-1][j]);
                    }
                }
            }
        }

        int max = 0;
        for (int i = 0; i < W+1; i++) {
            max = Math.max(max, dp[T][i]);
        }

        System.out.println(max);
    }

}