import java.io.*;
import java.util.*;

public class BJ_11404_플로이드 {
  static final int MAX = 100000 * 100 + 1;
  static int N, M;
  public static void main(String... args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    M = Integer.parseInt(br.readLine());

    int[][] dp = new int[N + 1][N + 1];

    for(int i=1; i<=N; i++) {
      for(int j=1; j<=N; j++) {
        if(i == j) {
          dp[i][j] = 0;
          continue;
        }
        dp[i][j] = MAX;
      }
    }

    for(int i=0; i<M; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      dp[a][b] = Math.min(dp[a][b], w);
    }

    floyd(dp);

    StringBuilder sb = new StringBuilder();
    for(int i=1; i<=N; i++) {
      for(int j=1; j<=N; j++) {
        if(dp[i][j] == MAX) {
          sb.append(0);
        } else {
          sb.append(dp[i][j]);
        }
        sb.append(" ");
      }
      sb.append("\n");
    }
    System.out.println(sb.toString().trim());
  }

  static void floyd(int[][] dp) {
    for(int k=1; k<=N; k++) {
      for(int i=1; i<=N; i++) {
        for(int j=1; j<=N; j++) {
          dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
        }
      }
    }
  }
}