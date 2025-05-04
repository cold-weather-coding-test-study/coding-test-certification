import java.util.*;

public class BJ_2411_아이템_먹기 {
  static int N, M, A, B;
  static boolean[][] blocked = new boolean[101][101];
  static List<Point> items = new ArrayList<>();

  static class Point implements Comparable<Point> {
    int x, y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int compareTo(Point o) {
      if (this.x == o.x) return this.y - o.y;
      return this.x - o.x;
    }
  }

  static int path(Point from, Point to) {
    int dx = to.x - from.x;
    int dy = to.y - from.y;
    if (dx < 0 || dy < 0) return 0;

    int[][] dp = new int[dx + 1][dy + 1];
    dp[0][0] = blocked[from.x][from.y] ? 0 : 1;

    for (int i = 0; i <= dx; i++) {
      for (int j = 0; j <= dy; j++) {
        int x = from.x + i;
        int y = from.y + j;
        if (blocked[x][y]) {
          dp[i][j] = 0;
          continue;
        }
        if (i > 0) dp[i][j] += dp[i - 1][j];
        if (j > 0) dp[i][j] += dp[i][j - 1];
      }
    }

    return dp[dx][dy];
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    M = sc.nextInt();
    A = sc.nextInt();
    B = sc.nextInt();

    items.add(new Point(1, 1)); // 시작
    for (int i = 0; i < A; i++) {
      int x = sc.nextInt();
      int y = sc.nextInt();
      items.add(new Point(x, y));
    }
    for (int i = 0; i < B; i++) {
      int x = sc.nextInt();
      int y = sc.nextInt();
      blocked[x][y] = true;
    }
    items.add(new Point(N, M)); // 도착

    Collections.sort(items);

    int result = 1;
    for (int i = 1; i < items.size(); i++) {
      int ways = path(items.get(i - 1), items.get(i));
      result *= ways;
    }

    System.out.println(result);
  }
}
