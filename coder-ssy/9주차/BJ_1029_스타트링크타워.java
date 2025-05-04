import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class BJ_1029_스타트링크타워 {
  static final String[][] DIGITS = {
      {"###", "#.#", "#.#", "#.#", "###"}, // 0
      {"..#", "..#", "..#", "..#", "..#"}, // 1
      {"###", "..#", "###", "#..", "###"}, // 2
      {"###", "..#", "###", "..#", "###"}, // 3
      {"#.#", "#.#", "###", "..#", "..#"}, // 4
      {"###", "#..", "###", "..#", "###"}, // 5
      {"###", "#..", "###", "#.#", "###"}, // 6
      {"###", "..#", "..#", "..#", "..#"}, // 7
      {"###", "#.#", "###", "#.#", "###"}, // 8
      {"###", "#.#", "###", "..#", "###"}  // 9
  };

  static int N;
  static String[] input = new String[5];
  static List<List<Integer>> candidates = new ArrayList<>();

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = Integer.parseInt(sc.nextLine());

    for (int i = 0; i < 5; i++) {
      input[i] = sc.nextLine();
    }

    // 각 자리마다 가능한 숫자 후보 구하기
    for (int d = 0; d < N; d++) {
      List<Integer> list = new ArrayList<>();
      for (int digit = 0; digit <= 9; digit++) {
        if (canBeDigit(d, digit)) {
          list.add(digit);
        }
      }
      if (list.isEmpty()) {
        System.out.println(-1);
        return;
      }
      candidates.add(list);
    }

    // 결과 저장용 변수
    AtomicLong sum = new AtomicLong(0);
    AtomicLong count = new AtomicLong(0);

    // 모든 가능한 조합 탐색
    generate(0, 0, sum, count);

    System.out.printf("%.5f\n", (double) sum.get() / count.get());
  }

  // 가능한 숫자 조합 생성
  static void generate(int idx, long current, AtomicLong sum, AtomicLong count) {
    if (idx == N) {
      sum.addAndGet(current);
      count.incrementAndGet();
      return;
    }

    for (int d : candidates.get(idx)) {
      generate(idx + 1, current * 10 + d, sum, count);
    }
  }

  // 특정 자리에서 digit이 될 수 있는지 확인
  static boolean canBeDigit(int d, int digit) {
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 3; j++) {
        char board = input[i].charAt(d * 4 + j);
        char standard = DIGITS[digit][i].charAt(j);
        if (board == '#' && standard == '.') {
          return false; // 꺼져 있어야 할 곳이 켜져있으면 불가능
        }
      }
    }
    return true;
  }
}
