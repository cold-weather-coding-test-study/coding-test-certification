import java.util.*;

public class BJ_1106_호텔 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int C = sc.nextInt(); // 목표 고객 수
    int N = sc.nextInt(); // 도시 수

    int[] cost = new int[N];
    int[] customer = new int[N];

    for (int i = 0; i < N; i++) {
      cost[i] = sc.nextInt();
      customer[i] = sc.nextInt();
    }

    int MAX = 100_001;
    int[] dp = new int[MAX];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;

    for (int i = 0; i < N; i++) {
      for (int j = customer[i]; j < MAX; j++) {
        if (dp[j - customer[i]] != Integer.MAX_VALUE) {
          dp[j] = Math.min(dp[j], dp[j - customer[i]] + cost[i]);
        }
      }
    }

    int answer = Integer.MAX_VALUE;
    for (int i = C; i < MAX; i++) {
      answer = Math.min(answer, dp[i]);
    }

    System.out.println(answer);
  }
}
