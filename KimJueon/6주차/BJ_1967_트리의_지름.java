import java.util.*;

public class BJ_1967_트리의_지름 {
  static List<Node>[] tree;
  static boolean[] visited;
  static int maxDist = 0;
  static int farNode = 0;

  static class Node {
    int to, weight;
    Node(int to, int weight) {
      this.to = to;
      this.weight = weight;
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();

    tree = new ArrayList[n + 1];
    for (int i = 0; i <= n; i++) {
      tree[i] = new ArrayList<>();
    }

    for (int i = 0; i < n - 1; i++) {
      int from = sc.nextInt();
      int to = sc.nextInt();
      int weight = sc.nextInt();
      tree[from].add(new Node(to, weight));
      tree[to].add(new Node(from, weight)); // 양방향
    }

    visited = new boolean[n + 1];
    dfs(1, 0); // 아무 노드(1)에서 시작

    visited = new boolean[n + 1];
    maxDist = 0;
    dfs(farNode, 0); // 가장 먼 노드에서 다시 DFS

    System.out.println(maxDist);
  }

  static void dfs(int now, int dist) {
    visited[now] = true;

    if (dist > maxDist) {
      maxDist = dist;
      farNode = now;
    }

    for (Node next : tree[now]) {
      if (!visited[next.to]) {
        dfs(next.to, dist + next.weight);
      }
    }
  }
}
