import java.util.*;

public class BJ_1158_요세푸스_문제 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt(); // 사람 수
    int K = sc.nextInt(); // 제거할 K번째

    List<Integer> people = new LinkedList<>();
    for (int i = 1; i <= N; i++) {
      people.add(i);
    }

    StringBuilder sb = new StringBuilder();
    sb.append("<");

    int index = 0; // 시작 인덱스

    while (!people.isEmpty()) {
      index = (index + K - 1) % people.size(); // 다음 제거 인덱스
      sb.append(people.remove(index));
      if (!people.isEmpty()) {
        sb.append(", ");
      }
    }

    sb.append(">");
    System.out.println(sb.toString());
  }
}
