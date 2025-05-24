import java.util.*;
import java.util.stream.Collectors;

public class BJ_1035_조각_움직이기 {

  static final int N = 5;
  static List<Point> pieces = new ArrayList<>();
  static List<List<Point>> targets = new ArrayList<>();
  static int min = Integer.MAX_VALUE;

  static class Point {
    int y, x;
    Point(int y, int x) {
      this.y = y;
      this.x = x;
    }

    int dist(Point p) {
      return Math.abs(this.y - p.y) + Math.abs(this.x - p.x);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Point point = (Point) o;
      return y == point.y && x == point.x;
    }

    @Override
    public int hashCode() {
      return Objects.hash(y, x);
    }

    @Override
    public String toString() {
      return y + "," + x;
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    char[][] board = new char[N][N];

    for (int i = 0; i < N; i++) {
      String line = sc.next();
      for (int j = 0; j < N; j++) {
        board[i][j] = line.charAt(j);
        if (board[i][j] == '*') {
          pieces.add(new Point(i, j));
        }
      }
    }

    generateConnectedBlocks();

    for (List<Point> target : targets) {
      if (target.size() != pieces.size()) continue;
      boolean[] visited = new boolean[pieces.size()];
      permute(pieces, target, visited, 0, 0);
    }

    System.out.println(min);
  }

  static void permute(List<Point> from, List<Point> to, boolean[] visited, int depth, int totalDist) {
    if (totalDist >= min) return;

    if (depth == from.size()) {
      min = Math.min(min, totalDist);
      return;
    }

    for (int i = 0; i < to.size(); i++) {
      if (!visited[i]) {
        visited[i] = true;
        permute(from, to, visited, depth + 1, totalDist + from.get(depth).dist(to.get(i)));
        visited[i] = false;
      }
    }
  }

  static void generateConnectedBlocks() {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        bfs(i, j);
      }
    }
  }

  static void bfs(int sy, int sx) {
    Queue<List<Point>> queue = new LinkedList<>();
    Set<String> seen = new HashSet<>();
    List<Point> start = new ArrayList<>();
    start.add(new Point(sy, sx));
    queue.offer(start);
    seen.add(toKey(start));

    while (!queue.isEmpty()) {
      List<Point> current = queue.poll();

      if (current.size() == pieces.size()) {
        targets.add(current);
        continue;
      }

      for (Point p : current) {
        for (int[] d : new int[][]{{1,0},{-1,0},{0,1},{0,-1}}) {
          int ny = p.y + d[0], nx = p.x + d[1];
          Point np = new Point(ny, nx);

          if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
          if (current.contains(np)) continue;

          List<Point> next = new ArrayList<>(current);
          next.add(np);
          next.sort(Comparator.comparingInt((Point pt) -> pt.y).thenComparingInt(pt -> pt.x));

          String key = toKey(next);
          if (!seen.contains(key)) {
            seen.add(key);
            queue.offer(next);
          }
        }
      }
    }
  }

  static String toKey(List<Point> list) {
    return list.stream()
        .sorted(Comparator.comparingInt((Point pt) -> pt.y).thenComparingInt(pt -> pt.x))
        .map(Point::toString)
        .collect(Collectors.joining(";"));
  }
}
