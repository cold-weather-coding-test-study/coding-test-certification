import java.util.*;

public class BJ_2194_유닛_이동시키기 {
  static int N, M, A, B, K;
  static boolean[][] map;
  static boolean[][] visited;

  static int[] dy = {-1, 1, 0, 0};
  static int[] dx = {0, 0, -1, 1};

  static class Point {
    int y, x, dist;
    Point(int y, int x, int dist) {
      this.y = y;
      this.x = x;
      this.dist = dist;
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    M = sc.nextInt();
    A = sc.nextInt();
    B = sc.nextInt();
    K = sc.nextInt();

    map = new boolean[N + 1][M + 1];
    visited = new boolean[N + 1][M + 1];

    for (int i = 0; i < K; i++) {
      int r = sc.nextInt();
      int c = sc.nextInt();
      map[r][c] = true;
    }

    int sy = sc.nextInt();
    int sx = sc.nextInt();
    int ey = sc.nextInt();
    int ex = sc.nextInt();

    int result = bfs(sy, sx, ey, ex);
    System.out.println(result);
  }

  static int bfs(int sy, int sx, int ey, int ex) {
    Queue<Point> q = new LinkedList<>();
    q.add(new Point(sy, sx, 0));
    visited[sy][sx] = true;

    while (!q.isEmpty()) {
      Point cur = q.poll();

      if (cur.y == ey && cur.x == ex) return cur.dist;

      for (int d = 0; d < 4; d++) {
        int ny = cur.y + dy[d];
        int nx = cur.x + dx[d];

        if (ny < 1 || nx < 1 || ny + A - 1 > N || nx + B - 1 > M) continue;
        if (visited[ny][nx]) continue;
        if (!canMove(ny, nx)) continue;

        visited[ny][nx] = true;
        q.add(new Point(ny, nx, cur.dist + 1));
      }
    }

    return -1;
  }

  static boolean canMove(int y, int x) {
    for (int i = y; i < y + A; i++) {
      for (int j = x; j < x + B; j++) {
        if (map[i][j]) return false;
      }
    }
    return true;
  }
}
