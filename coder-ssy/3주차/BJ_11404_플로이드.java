import java.util.Scanner;

public class BJ_11404_플로이드 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt(); // 도시 수
    int m = sc.nextInt(); // 버스 수

    int INF = 1_000_000_000;
    int[][] dist = new int[n + 1][n + 1];

    // 초기화
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        if (i == j) dist[i][j] = 0;
        else dist[i][j] = INF;
      }
    }

    // 버스 정보 입력
    for (int i = 0; i < m; i++) {
      int from = sc.nextInt();
      int to = sc.nextInt();
      int cost = sc.nextInt();

      dist[from][to] = Math.min(dist[from][to], cost); // 여러 개일 경우 최소값 유지
    }

    // 플로이드-워셜 알고리즘
    for (int k = 1; k <= n; k++) {
      for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
          if (dist[i][k] + dist[k][j] < dist[i][j]) {
            dist[i][j] = dist[i][k] + dist[k][j];
          }
        }
      }
    }

    // 출력
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        if (dist[i][j] == INF) System.out.print("0 ");
        else System.out.print(dist[i][j] + " ");
      }
      System.out.println();
    }
  }
}
