package DongHeonKim.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem1600 {

    // 위치 정보를 저장하는 클래스
    static class Pos {
        int y;         // 현재 위치의 y좌표
        int x;         // 현재 위치의 x좌표
        int remainK;   // 남은 말 점프 횟수 (K)
        int step;      // 현재까지 이동한 횟수

        public Pos(int y, int x, int remainK, int step) {
            this.y = y;
            this.x = x;
            this.remainK = remainK;
            this.step = step;
        }
    }

    // 원숭이 이동 방향 (상, 하, 좌, 우)
    static int[] monkeyDy = {-1, 1, 0, 0};
    static int[] monkeyDx = {0, 0, -1, 1};

    // 말 점프 이동 방향 (체스 나이트 이동 방식)
    static int[] hourseDy = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] hourseDx = {-2, -1, 1, 2, -2, -1, 1, 2};

    static int[][] board;  // 보드 정보 (0: 이동 가능, 1: 장애물)
    static int width, height;
    static boolean[][][] visited; // 방문 여부 (x, y, 남은 말 점프 횟수)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 말 점프 가능 횟수 입력
        int K = Integer.parseInt(br.readLine());

        // 보드의 가로, 세로 크기 입력
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        // 보드 초기화
        board = new int[height][width];
        for (int y = 0; y < height; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < width; x++) {
                board[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // 방문 여부 배열 초기화
        visited = new boolean[height][width][K + 1];

        int result = -1; // 결과값 (-1이면 도착 불가능)
        Queue<Pos> bfsQ = new LinkedList<>();
        bfsQ.add(new Pos(0, 0, K, 0)); // 시작점 (0,0)에서 시작, 말 점프 가능 횟수 K

        // BFS 탐색 시작
        while (!bfsQ.isEmpty()) {
            Pos curPos = bfsQ.poll();
            int curY = curPos.y;
            int curX = curPos.x;

            // 목표 지점 도착 시 종료
            if (curY == height - 1 && curX == width - 1) {
                result = curPos.step;
                break;
            }

            int tempY, tempX;

            // 원숭이 이동 (상, 하, 좌, 우)
            for (int k = 0; k < 4; k++) {
                tempY = curY + monkeyDy[k];
                tempX = curX + monkeyDx[k];

                // 이동 가능 여부 확인
                if (!isValid(tempY, tempX, curPos.remainK)) {
                    continue;
                }

                // 방문 체크 후 큐에 삽입
                visited[tempY][tempX][curPos.remainK] = true;
                bfsQ.add(new Pos(tempY, tempX, curPos.remainK, curPos.step + 1));
            }

            // 남은 말 점프 횟수가 없으면 말 이동 불가
            if (curPos.remainK <= 0) {
                continue;
            }

            // 말 이동 (나이트 이동 방식)
            for (int k = 0; k < 8; k++) {
                tempY = curY + hourseDy[k];
                tempX = curX + hourseDx[k];

                // 이동 가능 여부 확인 (남은 말 점프 횟수 -1)
                if (!isValid(tempY, tempX, curPos.remainK - 1)) {
                    continue;
                }

                // 방문 체크 후 큐에 삽입
                visited[tempY][tempX][curPos.remainK - 1] = true;
                bfsQ.add(new Pos(tempY, tempX, curPos.remainK - 1, curPos.step + 1));
            }
        }

        // 최소 이동 횟수 출력 (도착 불가능하면 -1 출력)
        System.out.println(result);
    }

    // 이동 가능한 위치인지 확인하는 함수
    private static boolean isValid(int tempY, int tempX, int remainK) {
        return tempY >= 0 && tempX >= 0 && tempY < height && tempX < width
                && !visited[tempY][tempX][remainK] // 방문하지 않았는지 확인
                && board[tempY][tempX] == 0; // 장애물이 없는지 확인
    }
}
