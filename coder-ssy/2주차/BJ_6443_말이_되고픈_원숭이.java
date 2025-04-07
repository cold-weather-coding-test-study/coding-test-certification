import java.util.*;

class Node {
  int x, y, k, moves;
  public Node(int x, int y, int k, int moves) {
    this.x = x;
    this.y = y;
    this.k = k;
    this.moves = moves;
  }
}

public class BJ_6443_말이_되고픈_원숭이 {
  static int W, H, K;
  static int[][] map;
  static boolean[][][] visited;

  static int[] dx = {0, 0, 1, -1}; // 상하좌우 이동
  static int[] dy = {1, -1, 0, 0};
  static int[] hx = {-2, -1, 1, 2, 2, 1, -1, -2}; // 나이트 이동
  static int[] hy = {1, 2, 2, 1, -1, -2, -2, -1};

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    K = sc.nextInt();
    W = sc.nextInt();
    H = sc.nextInt();
    map = new int[H][W];
    visited = new boolean[H][W][K + 1];

    for (int i = 0; i < H; i++) {
      for (int j = 0; j < W; j++) {
        map[i][j] = sc.nextInt();
      }
    }

    System.out.println(bfs());
  }

  static int bfs() {
    Queue<Node> queue = new LinkedList<>();
    queue.add(new Node(0, 0, K, 0));
    visited[0][0][K] = true;

    while (!queue.isEmpty()) {
      Node cur = queue.poll();

      // 목표 지점 도착
      if (cur.x == H - 1 && cur.y == W - 1) {
        return cur.moves;
      }

      // 4방향 이동
      for (int d = 0; d < 4; d++) {
        int nx = cur.x + dx[d];
        int ny = cur.y + dy[d];
        if (isValid(nx, ny, cur.k)) {
          visited[nx][ny][cur.k] = true;
          queue.add(new Node(nx, ny, cur.k, cur.moves + 1));
        }
      }

      // 나이트 이동 (남은 기회가 있을 경우)
      if (cur.k > 0) {
        for (int d = 0; d < 8; d++) {
          int nx = cur.x + hx[d];
          int ny = cur.y + hy[d];
          if (isValid(nx, ny, cur.k - 1)) {
            visited[nx][ny][cur.k - 1] = true;
            queue.add(new Node(nx, ny, cur.k - 1, cur.moves + 1));
          }
        }
      }
    }
    return -1; // 도착 불가능한 경우
  }

  static boolean isValid(int x, int y, int k) {
    return x >= 0 && y >= 0 && x < H && y < W && map[x][y] == 0 && !visited[x][y][k];
  }
}
