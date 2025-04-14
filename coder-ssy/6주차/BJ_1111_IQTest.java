import java.util.Scanner;

public class BJ_1111_IQTest {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int[] arr = new int[N];

    for (int i = 0; i < N; i++) {
      arr[i] = sc.nextInt();
    }

    if (N == 1) {
      System.out.println("A");
    } else if (N == 2) {
      if (arr[0] == arr[1]) {
        System.out.println(arr[0]);
      } else {
        System.out.println("A");
      }
    } else {
      // N >= 3
      int a, b;
      boolean valid = true;

      // (arr[1] - arr[0]) / (arr[0] != arr[1]) → a
      if ((arr[1] - arr[0]) == 0) {
        a = 0;
      } else {
        if ((arr[2] - arr[1]) % (arr[1] - arr[0]) != 0) {
          System.out.println("B");
          return;
        }
        a = (arr[2] - arr[1]) / (arr[1] - arr[0]);
      }
      b = arr[1] - arr[0] * a;

      for (int i = 0; i < N - 1; i++) {
        if (arr[i + 1] != arr[i] * a + b) {
          valid = false;
          break;
        }
      }

      if (valid) {
        System.out.println(arr[N - 1] * a + b);
      } else {
        System.out.println("B");
      }
    }
  }
}
