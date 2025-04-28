import java.util.Scanner;

public class BJ_9084_동전 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt(); // 테스트 케이스 수

        while (T-- > 0) {
            int N = sc.nextInt(); // 동전 가지 수
            int[] coins = new int[N];

            for (int i = 0; i < N; i++) {
                coins[i] = sc.nextInt();
            }

            int M = sc.nextInt(); // 목표 금액

            int[] dp = new int[M + 1];
            dp[0] = 1; // 금액 0을 만드는 방법은 1개 (아무것도 안 쓰기)

            for (int coin : coins) {
                for (int j = coin; j <= M; j++) {
                    dp[j] += dp[j - coin];
                }
            }

            System.out.println(dp[M]);
        }
    }
}
