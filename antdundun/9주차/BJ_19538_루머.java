import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_19538_루머 {

    static int N, M;
    static ArrayList<Integer>[] adj;
    static boolean[] visit;
    static int[] ans;
    
    static class Node implements Comparable<Node> {
    	int node;
    	int time;
		public Node(int node, int time) {
			this.node = node;
			this.time = time;
		}
		@Override
		public int compareTo(Node o) {
			return this.time-o.time;
		}
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        
        adj = new ArrayList[N+1];
        visit = new boolean[N+1];
        ans = new int[N+1];
        
        Arrays.fill(ans, -1);
        
        for (int i = 1; i < N+1; i++) {
			adj[i] = new ArrayList<>();
		}
        
        for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			while (true) {
				int people = Integer.parseInt(st.nextToken());
				
				if (people == 0) break;
				
				adj[i].add(people);
			}
		}
        
        M = Integer.parseInt(br.readLine());
        
        PriorityQueue<Node> que = new PriorityQueue<>();
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
        	int people = Integer.parseInt(st.nextToken());
			que.add(new Node(people, 0));
			visit[people] = true;
		}
        
        while (!que.isEmpty()) {
        	Node cur = que.poll();
        	
        	ans[cur.node] = cur.time;
        	
        	for (int j = 0; j < adj[cur.node].size(); j++) {
        		int next = adj[cur.node].get(j);
        		
        		if (visit[next]) continue;
        		if (belive(next)) {
        			que.add(new Node(next, cur.time+1));
        			visit[next] = true;
        		}
			}
        }
        
        for (int i = 1; i < N+1; i++) {
			System.out.print(ans[i]+" ");
		}
    }

	private static boolean belive(int cur) {
		int cnt = 0;
		int len = adj[cur].size();
		
		for (int i = 0; i < len; i++) {
			int next = adj[cur].get(i);
			if (ans[next] > -1) cnt++;
		}
				
		if (cnt >= Math.ceil(len/2.0)) return true;
		return false;
	}

}
