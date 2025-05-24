import java.util.*;

public class BJ_17069_파이프_옮기기_2 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int[][] map = new int[N][N];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        map[i][j] = sc.nextInt();
      }
    }

    // dp[i][j][d] = (i,j)에 방향 d로 도달하는 방법 수
    long[][][] dp = new long[N][N][3];

    // 시작 위치 (0,1) 가로 방향
    dp[0][1][0] = 1;

    for (int i = 0; i < N; i++) {
      for (int j = 2; j < N; j++) {
        if (map[i][j] == 1) continue;

        // 가로
        dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];

        // 세로
        if (i >= 1)
          dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];

        // 대각선
        if (i >= 1 && map[i - 1][j] == 0 && map[i][j - 1] == 0)
          dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
      }
    }

    long result = dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];
    System.out.println(result);
  }
}
