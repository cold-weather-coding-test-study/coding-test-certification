import java.util.Scanner;

public class BJ_10844_쉬운_계단_수 {
  static final int MOD = 1_000_000_000;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    long[][] dp = new long[N + 1][10];

    // 초기값: 길이가 1일 때 1~9는 1개씩
    for (int i = 1; i <= 9; i++) {
      dp[1][i] = 1;
    }

    // 점화식 적용
    for (int i = 2; i <= N; i++) {
      for (int j = 0; j <= 9; j++) {
        if (j > 0) {
          dp[i][j] += dp[i - 1][j - 1];
        }
        if (j < 9) {
          dp[i][j] += dp[i - 1][j + 1];
        }
        dp[i][j] %= MOD;
      }
    }

    // 정답: 길이 N에서 0~9까지의 합
    long result = 0;
    for (int i = 0; i <= 9; i++) {
      result = (result + dp[N][i]) % MOD;
    }

    System.out.println(result);
  }
}
