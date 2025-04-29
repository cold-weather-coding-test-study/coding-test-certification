import java.util.*;

public class BJ_1890_점프 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] board = new int[n][n];
        long[][] dp = new long[n][n]; // 경로 수 저장 (최대 2^63-1 미만이므로 long 사용)

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        dp[0][0] = 1; // 시작점

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0 || board[i][j] == 0) continue; // 이동 불가능

                int jump = board[i][j];

                // 오른쪽으로 점프
                if (j + jump < n) {
                    dp[i][j + jump] += dp[i][j];
                }
                // 아래쪽으로 점프
                if (i + jump < n) {
                    dp[i + jump][j] += dp[i][j];
                }
            }
        }

        System.out.println(dp[n-1][n-1]);
    }
}
