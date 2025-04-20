import java.util.Scanner;

public class BJ_1926_그림 {
  static int n, m;
  static int[][] canvas;
  static boolean[][] visit;

  // 상, 하, 좌, 우 (대각선 x)
  static int[] dx = {0, 0, -1, 1};
  static int[] dy = {-1, 1, 0, 0};

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 입력 받기
    n = sc.nextInt(); // 세로
    m = sc.nextInt(); // 가로
    canvas = new int[n][m];
    visit = new boolean[n][m];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        canvas[i][j] = sc.nextInt();
      }
    }

    int pictureCount = 0; // 그림의 개수
    int maxArea = 0; // 가장 넓은 그림의 넓이

    // 모든 좌표를 순회하면서 DFS를 통해 그림을 탐색
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (canvas[i][j] == 1 && !visit[i][j]) {
          pictureCount++; // 새로운 그림 발견 시 개수 증가
          int area = dfs(i, j); // 해당 그림의 넓이 계산
          maxArea = Math.max(maxArea, area); // 가장 넓은 그림의 넓이 갱신
        }
      }
    }

    // 결과 출력
    System.out.println(pictureCount);
    System.out.println(maxArea);

    sc.close();
  }

  // DFS를 이용한 그림의 넓이 계산
  static int dfs(int x, int y) {
    visit[x][y] = true;
    int area = 1;

    // 4방향 탐색
    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
        if (canvas[nx][ny] == 1 && !visit[nx][ny]) {
          area += dfs(nx, ny);
        }
      }
    }

    return area;
  }
}
