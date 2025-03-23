import java.util.*;

public class BJ_숌_사이_수열 {

  static Scanner scan = new Scanner(System.in);
  static int n;
  static int[] seq, answer;
  static boolean[] visit;

  public static void main(String[] args) {

    init();
    if (backtrack(0)) {
      for (int idx = 0; idx < n * 2; idx++) {
        System.out.print(answer[idx] + " ");
      }
    } else {
      System.out.println(-1);
    }
  }

  private static void init() {
    n = scan.nextInt();
    seq = new int[n];
    visit = new boolean[n];
    answer = new int[n * 2];

    for (int idx = 0; idx < n; idx++) {
      seq[idx] = scan.nextInt();
    }
    Arrays.sort(seq);
    Arrays.fill(answer, -1);
  }

  private static boolean backtrack(int level) {
    if (level == n * 2) {
      return true;
    }
    if (answer[level] != -1) {
      return backtrack(level + 1);
    }

    for (int idx = 0; idx < n; idx++) {
      int next = level + seq[idx] + 1; // 2라면 현재 위치에서 2 x x 2

      if (visit[idx]) {
        continue;
      }
      if (next >= n * 2) {
        continue;
      }
      if (answer[level] != -1 || answer[next] != -1) {
        continue;
      }

      visit[idx] = true;
      answer[level] = seq[idx];
      answer[next] = seq[idx];

      if (backtrack(level + 1)) {
        return true;
      }

      visit[idx] = false;
      answer[level] = -1;
      answer[next] = -1;
    }

    return false;
  }
}
