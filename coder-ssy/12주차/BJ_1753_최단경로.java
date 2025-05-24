import java.util.*;

public class BJ_1753_최단경로 {
  static class Node implements Comparable<Node> {
    int to, weight;

    public Node(int to, int weight) {
      this.to = to;
      this.weight = weight;
    }

    public int compareTo(Node o) {
      return this.weight - o.weight;
    }
  }

  static final int INF = Integer.MAX_VALUE;
  static List<Node>[] graph;
  static int[] dist;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int V = sc.nextInt();
    int E = sc.nextInt();
    int K = sc.nextInt();

    graph = new ArrayList[V + 1];
    dist = new int[V + 1];

    for (int i = 1; i <= V; i++) {
      graph[i] = new ArrayList<>();
      dist[i] = INF;
    }

    for (int i = 0; i < E; i++) {
      int u = sc.nextInt();
      int v = sc.nextInt();
      int w = sc.nextInt();
      graph[u].add(new Node(v, w));
    }

    dijkstra(K);

    for (int i = 1; i <= V; i++) {
      if (dist[i] == INF) System.out.println("INF");
      else System.out.println(dist[i]);
    }
  }

  static void dijkstra(int start) {
    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.offer(new Node(start, 0));
    dist[start] = 0;

    while (!pq.isEmpty()) {
      Node now = pq.poll();

      if (dist[now.to] < now.weight) continue;

      for (Node next : graph[now.to]) {
        int cost = dist[now.to] + next.weight;
        if (cost < dist[next.to]) {
          dist[next.to] = cost;
          pq.offer(new Node(next.to, cost));
        }
      }
    }
  }
}
