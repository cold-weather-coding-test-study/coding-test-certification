import java.util.*;

public class BJ_1967_트리의_지름 {

  static class Node {
    int to;
    int weight;

    public Node(int to, int weight) {
      this.to = to;
      this.weight = weight;
    }
  }

  static int n;
  static List<Node>[] tree;
  static boolean[] visited;
  static int maxDistance = 0;
  static int farthestNode = 0;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();

    tree = new ArrayList[n + 1];
    for (int i = 1; i <= n; i++) {
      tree[i] = new ArrayList<>();
    }

    for (int i = 0; i < n - 1; i++) {
      int from = sc.nextInt();
      int to = sc.nextInt();
      int weight = sc.nextInt();

      tree[from].add(new Node(to, weight));
      tree[to].add(new Node(from, weight));
    }

    // 1. 임의의 노드 (1번)에서 가장 먼 노드를 찾음
    visited = new boolean[n + 1];
    dfs(1, 0);

    // 2. 가장 먼 노드에서 다시 DFS하여 최대 거리 구함
    visited = new boolean[n + 1];
    maxDistance = 0;
    dfs(farthestNode, 0);

    System.out.println(maxDistance);
  }

  static void dfs(int current, int distance) {
    visited[current] = true;

    if (distance > maxDistance) {
      maxDistance = distance;
      farthestNode = current;
    }

    for (Node next : tree[current]) {
      if (!visited[next.to]) {
        dfs(next.to, distance + next.weight);
      }
    }
  }
}
