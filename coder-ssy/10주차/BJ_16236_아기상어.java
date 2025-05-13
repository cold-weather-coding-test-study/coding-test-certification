import java.util.*;

public class BJ_16236_아기상어 {
  static int N;
  static int[][] map;
  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};
  static int sharkX, sharkY, sharkSize = 2, eatenFish = 0, totalTime = 0;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    map = new int[N][N];

    // 아기 상어의 초기 위치 찾기
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        map[i][j] = sc.nextInt();
        if (map[i][j] == 9) {
          sharkX = i;
          sharkY = j;
          map[i][j] = 0; // 상어 위치를 빈칸으로 설정
        }
      }
    }

    // BFS로 물고기 먹기 시뮬레이션 시작
    while (true) {
      int timeToEat = bfs();
      if (timeToEat == -1) break; // 더 이상 먹을 물고기가 없음
      totalTime += timeToEat;
    }

    System.out.println(totalTime);
    sc.close();
  }

  private static int bfs() {
    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visited = new boolean[N][N];
    queue.offer(new int[]{sharkX, sharkY, 0});
    visited[sharkX][sharkY] = true;

    List<int[]> possibleFish = new ArrayList<>();
    int minDistance = Integer.MAX_VALUE;

    while (!queue.isEmpty()) {
      int[] current = queue.poll();
      int x = current[0];
      int y = current[1];
      int distance = current[2];

      // 현재 위치에서 사방 탐색
      for (int d = 0; d < 4; d++) {
        int nx = x + dx[d];
        int ny = y + dy[d];

        if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;

        // 이동할 수 있는 경우
        if (map[nx][ny] <= sharkSize) {
          visited[nx][ny] = true;
          queue.offer(new int[]{nx, ny, distance + 1});

          // 먹을 수 있는 물고기인 경우
          if (map[nx][ny] > 0 && map[nx][ny] < sharkSize) {
            if (distance + 1 < minDistance) {
              minDistance = distance + 1;
              possibleFish.clear();
              possibleFish.add(new int[]{nx, ny, minDistance});
            } else if (distance + 1 == minDistance) {
              possibleFish.add(new int[]{nx, ny, minDistance});
            }
          }
        }
      }
    }

    // 먹을 수 있는 물고기가 있으면 가장 위, 가장 왼쪽 물고기 선택
    if (!possibleFish.isEmpty()) {
      possibleFish.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
      int[] target = possibleFish.get(0);
      sharkX = target[0];
      sharkY = target[1];
      map[sharkX][sharkY] = 0;
      eatenFish++;

      // 상어 크기 증가 체크
      if (eatenFish == sharkSize) {
        sharkSize++;
        eatenFish = 0;
      }

      return target[2]; // 이동 거리 반환
    }

    return -1; // 먹을 물고기가 없는 경우
  }
}
