import java.util.*;

public class BJ_1495_기타리스트 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();  // 곡 개수
    int S = sc.nextInt();  // 시작 볼륨
    int M = sc.nextInt();  // 볼륨 최대값

    int[] V = new int[N + 1];  // 각 곡별 볼륨 변경량
    for (int i = 1; i <= N; i++) {
      V[i] = sc.nextInt();
    }

    // DP 테이블 정의 (i번째 곡에서 j 볼륨이 가능한가)
    boolean[][] dp = new boolean[N + 1][M + 1];
    dp[0][S] = true;  // 시작 볼륨 설정

    // DP 수행
    for (int i = 1; i <= N; i++) {
      for (int v = 0; v <= M; v++) {
        if (dp[i - 1][v]) {  // 이전 곡에서 v 볼륨이 가능하면
          if (v + V[i] <= M) dp[i][v + V[i]] = true;
          if (v - V[i] >= 0) dp[i][v - V[i]] = true;
        }
      }
    }

    // 마지막 곡에서 가능한 최대 볼륨 찾기
    int maxVolume = -1;
    for (int v = 0; v <= M; v++) {
      if (dp[N][v]) {
        maxVolume = Math.max(maxVolume, v);
      }
    }

    System.out.println(maxVolume);
  }
}
