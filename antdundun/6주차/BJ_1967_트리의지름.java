import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_1967_트리의지름 {
	
	static int N, max;
	static ArrayList<Node>[] adj;
	static boolean[] visit;
	
	static class Node {
		int child;
		int edge;
		public Node(int child, int edge) {
			this.child = child;
			this.edge = edge;
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N+1];
        
        for (int i = 1; i < N+1; i++) {
			adj[i] = new ArrayList<>();
		}
        
        for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int edge = Integer.parseInt(st.nextToken());
			
			adj[a].add(new Node(b,edge));
			adj[b].add(new Node(a,edge));
		}
        
        max = 0;
        for (int i = 1; i < N+1; i++) {
            visit = new boolean[N+1];
            dfs(i, 0);
		}
        
        System.out.println(max);
        
    }

	private static void dfs(int node, int sum) {
		visit[node] = true;
		max = Math.max(sum, max);
		
		for (Node cur : adj[node]) {
			int next = cur.child;
			int cost = cur.edge;
			if (!visit[next]) {
				dfs(next, sum+cost);
			}
		}
		
	}

}
