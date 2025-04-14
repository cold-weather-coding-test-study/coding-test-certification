import java.util.Scanner;

public class BJ_2089_진법변환 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    long x = sc.nextLong();
    int b = sc.nextInt();

    // 예외 처리: x == 0
    if (x == 0) {
      System.out.println(0);
      return;
    }

    StringBuilder sb = new StringBuilder();

    // 음의 진법
    if (b < 0) {
      while (x != 0) {
        long remainder = x % b;
        x = x / b;

        if (remainder < 0) {
          remainder += Math.abs(b);
          x += 1;
        }

        sb.append(remainder);
      }

      System.out.println(sb.reverse());

    } else { // 양의 진법
      boolean isNegative = x < 0;
      x = Math.abs(x);

      while (x > 0) {
        sb.append(x % b);
        x /= b;
      }

      if (isNegative) sb.append("-");
      System.out.println(sb.reverse());
    }
  }
}
