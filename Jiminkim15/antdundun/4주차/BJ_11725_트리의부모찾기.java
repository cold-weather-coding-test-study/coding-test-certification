import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_11725_트리의부모찾기 {
	
	static int N;
	static ArrayList<Integer>[] adj;
	static int[] parent;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        
        adj = new ArrayList[N+1];
        for (int i = 1; i < N+1; i++) {
			adj[i] = new ArrayList<>();
		}
        
        parent = new int[N+1];
        parent[1] = 1;
        
        for (int i = 0; i < N-1; i++) {
        	st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			adj[a].add(b);
			adj[b].add(a);
		}
        
        dfs(1);
        
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < N+1; i++) {
			sb.append(parent[i]+"\n");
		}
        
        System.out.println(sb.toString());
    }

	private static void dfs(int node) {
		for (int i = 0; i < adj[node].size(); i++) {
			int child = adj[node].get(i);
			if (parent[child] > 0) continue;
			parent[child] = node;
			dfs(child);
		}
		
	}

}
