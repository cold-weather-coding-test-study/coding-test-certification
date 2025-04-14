import java.util.*;

public class BJ_18430_무기_공학 {
  static int N, M;
  static int[][] board;
  static boolean[][] visited;
  static int max = 0;

  // 부메랑 4가지 모양 (중심 포함 3칸)
  static int[][][] shapes = {
      {{0, 0}, {1, 0}, {0, 1}},     // ┗ 모양
      {{0, 0}, {0, 1}, {-1, 0}},    // ┛ 모양
      {{0, 0}, {0, -1}, {1, 0}},    // ┏ 모양
      {{0, 0}, {0, -1}, {-1, 0}}    // ┓ 모양
  };

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    M = sc.nextInt();
    board = new int[N][M];
    visited = new boolean[N][M];

    for (int i = 0; i < N; i++)
      for (int j = 0; j < M; j++)
        board[i][j] = sc.nextInt();

    dfs(0, 0, 0);
    System.out.println(max);
  }

  static void dfs(int x, int y, int sum) {
    if (x == N) {
      max = Math.max(max, sum);
      return;
    }

    // 다음 좌표 계산
    int nextX = (y == M - 1) ? x + 1 : x;
    int nextY = (y == M - 1) ? 0 : y + 1;

    // 부메랑을 현재 위치에 놓지 않는 경우
    dfs(nextX, nextY, sum);

    // 부메랑을 현재 위치에 놓는 경우 (4방향)
    if (!visited[x][y]) {
      for (int[][] shape : shapes) {
        boolean canPlace = true;
        int strength = 0;
        List<int[]> coords = new ArrayList<>();

        for (int[] d : shape) {
          int nx = x + d[0];
          int ny = y + d[1];
          if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) {
            canPlace = false;
            break;
          }
          coords.add(new int[]{nx, ny});
        }

        if (canPlace) {
          for (int k = 0; k < coords.size(); k++) {
            int[] c = coords.get(k);
            visited[c[0]][c[1]] = true;
            // 중심은 항상 첫 번째 좌표 (shapes에서 중심이 항상 [0][0]임)
            strength += board[c[0]][c[1]] * (k == 0 ? 2 : 1);
          }

          dfs(nextX, nextY, sum + strength);

          for (int[] c : coords) {
            visited[c[0]][c[1]] = false;
          }
        }
      }
    }
  }
}
