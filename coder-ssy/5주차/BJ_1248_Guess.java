import java.util.Scanner;

public class BJ_1248_Guess {

  static int n;
  static char[][] sign;
  static int[] result;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    String input = sc.next();

    sign = new char[n][n];
    result = new int[n];

    // 부호 정보를 2차원 배열로 변환
    int idx = 0;
    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        sign[i][j] = input.charAt(idx++);
      }
    }

    // 백트래킹 시작
    dfs(0);
  }

  // DFS로 수열을 구성
  static boolean dfs(int depth) {
    if (depth == n) {
      // 수열 완성되면 출력하고 종료
      for (int i = 0; i < n; i++) {
        System.out.print(result[i] + " ");
      }
      return true;
    }

    for (int num = -10; num <= 10; num++) {
      result[depth] = num;
      if (isValid(depth)) {
        if (dfs(depth + 1)) return true;
      }
    }
    return false;
  }

  // 현재 수열이 유효한지 확인
  static boolean isValid(int depth) {
    int sum = 0;
    for (int i = depth; i >= 0; i--) {
      sum += result[i];
      char s = sign[i][depth];
      if (s == '+' && sum <= 0) return false;
      if (s == '-' && sum >= 0) return false;
      if (s == '0' && sum != 0) return false;
    }
    return true;
  }
}
