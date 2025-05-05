import java.util.*;

public class BJ_8972_미친_아두이노 {
  static int R, C;
  static char[][] map;
  static int[] dx = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
  static int[] dy = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};

  static class Point {
    int x, y;
    Point(int x, int y) { this.x = x; this.y = y; }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    R = sc.nextInt();
    C = sc.nextInt();
    map = new char[R][C];

    Point player = null;
    List<Point> enemies = new ArrayList<>();

    for (int i = 0; i < R; i++) {
      String line = sc.next();
      for (int j = 0; j < C; j++) {
        map[i][j] = line.charAt(j);
        if (map[i][j] == 'I') player = new Point(i, j);
        else if (map[i][j] == 'R') enemies.add(new Point(i, j));
      }
    }

    String moves = sc.next();
    for (int turn = 0; turn < moves.length(); turn++) {
      int dir = moves.charAt(turn) - '0';
      player.x += dx[dir];
      player.y += dy[dir];

      // 1. 종수가 적군 위로 이동 → 패배
      for (Point e : enemies) {
        if (e.x == player.x && e.y == player.y) {
          System.out.println("kraj " + (turn + 1));
          return;
        }
      }

      // 2. 미친 아두이노들 이동
      Map<String, List<Point>> nextEnemyMap = new HashMap<>();
      for (Point e : enemies) {
        int minDist = Integer.MAX_VALUE;
        int bestX = e.x, bestY = e.y;

        for (int d = 1; d <= 9; d++) {
          int nx = e.x + dx[d];
          int ny = e.y + dy[d];

          if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;

          int dist = Math.abs(player.x - nx) + Math.abs(player.y - ny);
          if (dist < minDist) {
            minDist = dist;
            bestX = nx;
            bestY = ny;
          }
        }

        String key = bestX + "," + bestY;
        nextEnemyMap.putIfAbsent(key, new ArrayList<>());
        nextEnemyMap.get(key).add(new Point(bestX, bestY));
      }

      // 3. 새 적 리스트 생성, 폭발 처리
      enemies.clear();
      for (String key : nextEnemyMap.keySet()) {
        List<Point> group = nextEnemyMap.get(key);
        if (group.size() == 1) {
          Point p = group.get(0);
          enemies.add(p);
        }
      }

      // 4. 적이 종수 위로 이동 → 패배
      for (Point e : enemies) {
        if (e.x == player.x && e.y == player.y) {
          System.out.println("kraj " + (turn + 1));
          return;
        }
      }
    }

    // 5. 게임 끝났을 때 최종 맵 출력
    char[][] result = new char[R][C];
    for (char[] row : result) Arrays.fill(row, '.');

    result[player.x][player.y] = 'I';
    for (Point e : enemies) {
      result[e.x][e.y] = 'R';
    }

    for (int i = 0; i < R; i++) {
      System.out.println(new String(result[i]));
    }
  }
}
