import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Edge>[] adj;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        
        if(n == 1){
            System.out.println(0);
            return;
        }

        
        adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++){
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i < n - 1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adj[parent].add(new Edge(child, weight));
            adj[child].add(new Edge(parent, weight));
        }

        // 1. 임의로 1번 노드에서 BFS를 진행하여 가장 먼 노드 x 찾기
        NodeInfo x = bfs(1);
        // 2. x에서 BFS를 다시 진행하여 가장 먼 노드 y 찾기
        NodeInfo y = bfs(x.node);

        // x와 y 사이의 거리가 트리의 지름
        System.out.println(y.dist);
    }

    // start 노드로부터 가장 멀리 있는 노드와 그 거리를 반환하는 BFS
    static NodeInfo bfs(int start){
        Queue<Integer> queue = new LinkedList<>();
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        dist[start] = 0;
        queue.offer(start);

        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(Edge e : adj[cur]){
                int nxt = e.to;
                int w = e.weight;

                if(dist[nxt] == -1){
                    dist[nxt] = dist[cur] + w;
                    queue.offer(nxt);
                }
            }
        }

        int maxDist = 0;
        int maxNode = start;
        for(int i = 1; i <= n; i++){
            if(dist[i] > maxDist){
                maxDist = dist[i];
                maxNode = i;
            }
        }

        return new NodeInfo(maxNode, maxDist);
    }

    static class Edge {
        int to, weight;
        Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    static class NodeInfo {
        int node, dist;
        NodeInfo(int node, int dist){
            this.node = node;
            this.dist = dist;
        }
    }
}