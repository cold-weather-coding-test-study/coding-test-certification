import java.io.*;
import java.util.*;

public class BJ_11725_트리의_부모_찾기 {
  static List<List<Integer>> tree = new ArrayList<>();
  static int[] parent;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());

    // 트리 초기화
    for (int i = 0; i <= n; i++) {
      tree.add(new ArrayList<>());
    }

    // 입력받은 간선 정보로 트리 구성
    for (int i = 0; i < n - 1; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      tree.get(a).add(b);
      tree.get(b).add(a);
    }

    parent = new int[n + 1];

    // BFS로 부모 노드 찾기
    bfs(1);

    // 결과 출력 (2번 노드부터)
    StringBuilder sb = new StringBuilder();
    for (int i = 2; i <= n; i++) {
      sb.append(parent[i]).append("\n");
    }
    System.out.print(sb);
  }

  private static void bfs(int start) {
    Queue<Integer> queue = new LinkedList<>();
    boolean[] visited = new boolean[parent.length];

    queue.add(start);
    visited[start] = true;

    while (!queue.isEmpty()) {
      int current = queue.poll();

      for (int next : tree.get(current)) {
        if (!visited[next]) {
          visited[next] = true;
          parent[next] = current;
          queue.add(next);
        }
      }
    }
  }
}
