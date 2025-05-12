import java.util.*;

public class BJ_14502_연구소 {
  static int N, M, maxSafeArea;
  static int[][] lab, tempLab;
  static List<int[]> virusPositions = new ArrayList<>();
  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    M = sc.nextInt();
    lab = new int[N][M];

    // 연구소 입력
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        lab[i][j] = sc.nextInt();
        if (lab[i][j] == 2) {
          virusPositions.add(new int[]{i, j});
        }
      }
    }

    maxSafeArea = 0;
    buildWall(0);
    System.out.println(maxSafeArea);
    sc.close();
  }

  // 벽 세우기
  private static void buildWall(int wallCount) {
    if (wallCount == 3) {
      spreadVirus();
      maxSafeArea = Math.max(maxSafeArea, countSafeArea());
      return;
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (lab[i][j] == 0) {
          lab[i][j] = 1;  // 벽 세우기
          buildWall(wallCount + 1);
          lab[i][j] = 0;  // 벽 제거 (백트래킹)
        }
      }
    }
  }

  // 바이러스 확산
  private static void spreadVirus() {
    tempLab = new int[N][M];
    for (int i = 0; i < N; i++) {
      tempLab[i] = lab[i].clone();
    }

    Queue<int[]> queue = new LinkedList<>();
    for (int[] pos : virusPositions) {
      queue.offer(pos);
    }

    while (!queue.isEmpty()) {
      int[] current = queue.poll();
      int x = current[0];
      int y = current[1];

      for (int d = 0; d < 4; d++) {
        int nx = x + dx[d];
        int ny = y + dy[d];

        if (nx >= 0 && nx < N && ny >= 0 && ny < M && tempLab[nx][ny] == 0) {
          tempLab[nx][ny] = 2;
          queue.offer(new int[]{nx, ny});
        }
      }
    }
  }

  // 안전 영역 계산
  private static int countSafeArea() {
    int safeArea = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (tempLab[i][j] == 0) {
          safeArea++;
        }
      }
    }
    return safeArea;
  }
}
