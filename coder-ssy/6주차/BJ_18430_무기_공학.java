import java.util.Scanner;

public class BJ_18430_무기_공학 {

  static int N, M;
  static int[][] board;
  static boolean[][] visited;
  static int max = 0;

  // 4가지 부메랑 모양 (중심, 날개1, 날개2)
  static int[][][] boomerangs = {
      { {0, 0}, {1, 0}, {0, -1} },  // 아래 + 왼쪽
      { {0, 0}, {1, 0}, {0, 1} },   // 아래 + 오른쪽
      { {0, 0}, {-1, 0}, {0, -1} }, // 위 + 왼쪽
      { {0, 0}, {-1, 0}, {0, 1} }   // 위 + 오른쪽
  };

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    M = sc.nextInt();

    board = new int[N][M];
    visited = new boolean[N][M];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        board[i][j] = sc.nextInt();
      }
    }

    dfs(0, 0, 0);
    System.out.println(max);
  }

  static void dfs(int x, int y, int sum) {
    if (y == M) {  // 다음 행으로 이동
      y = 0;
      x++;
    }

    if (x == N) {
      max = Math.max(max, sum);
      return;
    }

    if (!visited[x][y]) {
      for (int[][] boomerang : boomerangs) {
        int bx = x, by = y;
        int ax = boomerang[1][0] + x, ay = boomerang[1][1] + y;
        int cx = boomerang[2][0] + x, cy = boomerang[2][1] + y;

        if (isIn(bx, by) && isIn(ax, ay) && isIn(cx, cy)
            && !visited[bx][by] && !visited[ax][ay] && !visited[cx][cy]) {

          visited[bx][by] = true;
          visited[ax][ay] = true;
          visited[cx][cy] = true;

          int power = board[bx][by] * 2 + board[ax][ay] + board[cx][cy];
          dfs(x, y + 1, sum + power);

          visited[bx][by] = false;
          visited[ax][ay] = false;
          visited[cx][cy] = false;
        }
      }
    }

    // 현재 칸을 사용하지 않고 넘어감
    dfs(x, y + 1, sum);
  }

  static boolean isIn(int x, int y) {
    return x >= 0 && x < N && y >= 0 && y < M;
  }
}
