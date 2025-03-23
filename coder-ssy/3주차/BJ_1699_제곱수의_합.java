import java.util.Scanner;

public class BJ_1699_제곱수의_합 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int[] dp = new int[N + 1];

    // 초기 세팅: 최악의 경우 모두 1^2로만 구성
    for (int i = 1; i <= N; i++) {
      dp[i] = i;
      for (int j = 1; j * j <= i; j++) {
        dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
      }
    }

    System.out.println(dp[N]);
  }
}
