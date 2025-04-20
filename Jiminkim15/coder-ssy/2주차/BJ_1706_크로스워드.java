import java.util.*;

public class BJ_1706_크로스워드 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int R = scanner.nextInt();
    int C = scanner.nextInt();
    scanner.nextLine(); // 개행 처리

    char[][] grid = new char[R][C];

    for (int i = 0; i < R; i++) {
      grid[i] = scanner.nextLine().toCharArray();
    }

    scanner.close();

    TreeSet<String> words = new TreeSet<>(); // 자동 정렬 저장

    // 1️⃣ 가로 단어 찾기
    for (int i = 0; i < R; i++) {
      StringBuilder word = new StringBuilder();
      for (int j = 0; j < C; j++) {
        if (grid[i][j] != '#') {
          word.append(grid[i][j]);
        } else {
          if (word.length() >= 2) words.add(word.toString());
          word.setLength(0); // 초기화
        }
      }
      if (word.length() >= 2) words.add(word.toString()); // 마지막 단어 체크
    }

    // 2️⃣ 세로 단어 찾기
    for (int j = 0; j < C; j++) {
      StringBuilder word = new StringBuilder();
      for (int i = 0; i < R; i++) {
        if (grid[i][j] != '#') {
          word.append(grid[i][j]);
        } else {
          if (word.length() >= 2) words.add(word.toString());
          word.setLength(0); // 초기화
        }
      }
      if (word.length() >= 2) words.add(word.toString()); // 마지막 단어 체크
    }

    // 3️⃣ 가장 앞서는 단어 출력
    System.out.println(words.first());
  }
}
