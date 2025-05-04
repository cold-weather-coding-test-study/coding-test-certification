import java.util.Scanner;

public class BJ_1030_프렉탈_평면 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int s = sc.nextInt();  // 시간
    int N = sc.nextInt();  // 분할 수
    int K = sc.nextInt();  // 검은 정사각형 크기
    int R1 = sc.nextInt(); int R2 = sc.nextInt();
    int C1 = sc.nextInt(); int C2 = sc.nextInt();

    for (int r = R1; r <= R2; r++) {
      StringBuilder sb = new StringBuilder();
      for (int c = C1; c <= C2; c++) {
        sb.append(isBlack(r, c, s, N, K) ? "1" : "0");
      }
      System.out.println(sb);
    }
  }

  static boolean isBlack(int r, int c, int s, int N, int K) {
    for (int i = 0; i < s; i++) {
      int nr = r % N;
      int nc = c % N;
      int start = (N - K) / 2;
      int end = (N + K) / 2;

      if (nr >= start && nr < end && nc >= start && nc < end) {
        return true;
      }

      r /= N;
      c /= N;
    }
    return false;
  }
}
