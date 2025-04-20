import java.io.*;
import java.util.*;

public class BJ_1018_체스판_다시_칠하기 {
  static char[][] board;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken()); // 행
    int M = Integer.parseInt(st.nextToken()); // 열

    board = new char[N][M];
    for (int i = 0; i < N; i++) {
      board[i] = br.readLine().toCharArray();
    }

    int answer = Integer.MAX_VALUE;

    // 8x8 체스판 시작점 (0~N-8), (0~M-8)
    for (int i = 0; i <= N - 8; i++) {
      for (int j = 0; j <= M - 8; j++) {
        answer = Math.min(answer, countRepaints(i, j));
      }
    }

    System.out.println(answer);
  }

  // 8x8 보드의 (x, y)에서 시작할 때 다시 칠해야 할 최소 칸 수
  private static int countRepaints(int x, int y) {
    int count1 = 0; // 시작이 W인 경우
    int count2 = 0; // 시작이 B인 경우

    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        // 기준: (i + j) % 2 == 0 이면 시작색, 1이면 반대색
        if ((i + j) % 2 == 0) {
          if (board[x + i][y + j] != 'W') count1++; // W로 시작할 경우
          if (board[x + i][y + j] != 'B') count2++; // B로 시작할 경우
        } else {
          if (board[x + i][y + j] != 'B') count1++;
          if (board[x + i][y + j] != 'W') count2++;
        }
      }
    }

    return Math.min(count1, count2);
  }
}
