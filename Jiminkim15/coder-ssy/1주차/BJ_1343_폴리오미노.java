import java.util.Scanner;

public class BJ_1343_폴리오미노 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String board = sc.nextLine(); // 보드판 입력
    sc.close();

    StringBuilder result = new StringBuilder();

    // "."을 기준으로 구간 분할
    String[] sections = board.split("\\.");

    for (int i = 0; i < sections.length; i++) {
      String part = sections[i];
      int length = part.length();

      if (length % 2 != 0) {
        System.out.println("-1");
        return;
      }

      int aCount = length / 4;
      int bCount = (length % 4) / 2;

      result.append("AAAA".repeat(aCount));
      result.append("BB".repeat(bCount));

      if (i < sections.length - 1) {
        result.append(".");
      }
    }

    System.out.println(result);
  }
}
