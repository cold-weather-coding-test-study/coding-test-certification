import java.util.*;

public class BJ_1469_숌_사이_수열 {
  static int N;
  static int[] X;
  static int[] sequence;
  static boolean[] used;
  static boolean found = false;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    X = new int[N];
    for (int i = 0; i < N; i++) {
      X[i] = sc.nextInt();
    }
    Arrays.sort(X);
    sequence = new int[2 * N];
    Arrays.fill(sequence, -1);  // 💡 중요: -1로 초기화
    used = new boolean[N];

    dfs(0);

    if (!found) {
      System.out.println(-1);
    }
  }

  static void dfs(int depth) {
    if (found) return;
    if (depth == 2 * N) {
      for (int num : sequence) {
        System.out.print(num + " ");
      }
      System.out.println();
      found = true;
      return;
    }

    if (sequence[depth] != -1) {  // 💡 변경된 조건
      dfs(depth + 1);
      return;
    }

    for (int i = 0; i < N; i++) {
      int val = X[i];
      if (used[i]) continue;

      int j = depth + val + 1;

      if (j < 2 * N && sequence[depth] == -1 && sequence[j] == -1) {
        sequence[depth] = val;
        sequence[j] = val;
        used[i] = true;

        dfs(depth + 1);

        sequence[depth] = -1;
        sequence[j] = -1;
        used[i] = false;
      }
    }
  }
}
