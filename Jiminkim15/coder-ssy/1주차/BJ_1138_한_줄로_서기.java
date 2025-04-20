import java.util.Scanner;

public class BJ_1138_한_줄로_서기 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt(); // 사람의 수
    int[] arr = new int[n]; // 자기보다 키 큰 사람이 앞에 몇 명 있었는지
    int[] result = new int[n]; // 최종 줄을 선 사람들의 배열

    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }
    sc.close();

    // 줄 세우기
    for (int i = 0; i < n; i++) {
      int count = arr[i];
      for (int j = 0; j < n; j++) {
        if (count == 0 && result[j] == 0) {
          result[j] = i + 1;
          break;
        } else if (result[j] == 0) {
          count--;
        }
      }
    }

    for (int num : result) {
      System.out.print(num + " ");
    }
  }
}
