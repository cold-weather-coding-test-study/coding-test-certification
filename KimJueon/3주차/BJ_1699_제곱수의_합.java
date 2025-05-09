import java.util.*;

public class BJ_제곱수의_합 {

  public static void main(String[] args) {
    int N;
    int[] dp;
    Scanner scanner = new Scanner(System.in);

    N = scanner.nextInt();
    dp = new int[N + 1];

    for (int i = 1; i <= N; i++) {
      dp[i] = i;
      for (int j = 1; j * j <= i; j++) {
        if (dp[i] > dp[i - j * j] + 1) {
          dp[i] = dp[i - j * j] + 1;
        }
      }
    }
    System.out.println(dp[N]);
  }
}
