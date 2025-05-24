import java.util.*;

public class BJ_1021_회전하는_큐 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();  // 큐 크기
    int M = sc.nextInt();  // 뽑을 수 개수

    LinkedList<Integer> deque = new LinkedList<>();
    for (int i = 1; i <= N; i++) {
      deque.add(i);
    }

    int[] targets = new int[M];
    for (int i = 0; i < M; i++) {
      targets[i] = sc.nextInt();
    }

    int count = 0;

    for (int target : targets) {
      int index = deque.indexOf(target);

      // 왼쪽 회전이 더 빠른 경우
      if (index <= deque.size() / 2) {
        while (deque.peekFirst() != target) {
          deque.addLast(deque.pollFirst()); // 왼쪽 회전
          count++;
        }
      } else {
        while (deque.peekFirst() != target) {
          deque.addFirst(deque.pollLast()); // 오른쪽 회전
          count++;
        }
      }

      deque.pollFirst(); // 제거
    }

    System.out.println(count);
  }
}
