import java.util.*;

public class BJ_14500_테트로미노 {
  static int N, M;
  static int[][] board;
  static boolean[][] visited;
  static int maxSum = 0;
  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};

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

    // 모든 칸에서 DFS 시작
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        visited[i][j] = true;
        dfs(i, j, 0, board[i][j]);
        visited[i][j] = false;
        checkTShape(i, j);
      }
    }

    System.out.println(maxSum);
    sc.close();
  }

  // 일반적인 테트로미노 (ㅗ 제외)
  private static void dfs(int x, int y, int depth, int sum) {
    if (depth == 3) {
      maxSum = Math.max(maxSum, sum);
      return;
    }

    for (int d = 0; d < 4; d++) {
      int nx = x + dx[d];
      int ny = y + dy[d];

      if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
        visited[nx][ny] = true;
        dfs(nx, ny, depth + 1, sum + board[nx][ny]);
        visited[nx][ny] = false;
      }
    }
  }

  // ㅗ 모양 테트로미노 처리
  private static void checkTShape(int x, int y) {
    // ㅗ, ㅜ, ㅏ, ㅓ 모양을 각각 따로 체크
    // 중심점 (x, y)를 기준으로 4방향에서의 모양을 확인
    int[][] tShapes = {
        {0, -1, 0, 1, -1, 0}, // ㅗ
        {0, -1, 0, 1, 1, 0},  // ㅜ
        {-1, 0, 1, 0, 0, -1}, // ㅏ
        {-1, 0, 1, 0, 0, 1}   // ㅓ
    };

    for (int[] shape : tShapes) {
      int sum = board[x][y];
      boolean isValid = true;

      for (int k = 0; k < 3; k++) {
        int nx = x + shape[k * 2];
        int ny = y + shape[k * 2 + 1];

        if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
          isValid = false;
          break;
        }

        sum += board[nx][ny];
      }

      if (isValid) {
        maxSum = Math.max(maxSum, sum);
      }
    }
  }
}
