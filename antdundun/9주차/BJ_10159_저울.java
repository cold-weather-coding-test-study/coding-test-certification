import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_10159_저울 {

    static int N, M;
    static ArrayList<Integer>[] big;
    static ArrayList<Integer>[] small;
    static boolean[] visit;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        big = new ArrayList[N+1]; // i보다 큰 인덱스를 넣는 리스트
        small = new ArrayList[N+1]; // i보다 작은 인덱스를 넣는 리스트
        
        for (int i = 1; i < N+1; i++) {
			big[i] = new ArrayList<>();
			small[i] = new ArrayList<>();
		}
        
        for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int b = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			big[s].add(b);
			small[b].add(s);
		}
        
        for (int i = 1; i < N+1; i++) {
        	visit = new boolean[N+1];
    		visit[i] = true;
			int bigCnt = findBig(i);
			int smallCnt = findSmall(i);
			
			sb.append(N-bigCnt-smallCnt-1+"\n");
		}
        
        System.out.println(sb.toString());
    }

	private static int findBig(int idx) {
		Queue<Integer> que = new LinkedList<>();
		que.add(idx);
		int cnt = 0;
		
		while (!que.isEmpty()) {
			int cur = que.poll();
			
			for (int i = 0; i < big[cur].size(); i++) {
				int next = big[cur].get(i);
				
				if (visit[next]) continue;
				que.add(next);
				visit[next] = true;
				cnt++;
			}
		}
		
		return cnt;
	}
	
	private static int findSmall(int idx) {
		Queue<Integer> que = new LinkedList<>();
		que.add(idx);
		int cnt = 0;
		
		while (!que.isEmpty()) {
			int cur = que.poll();
			
			for (int i = 0; i < small[cur].size(); i++) {
				int next = small[cur].get(i);
				
				if (visit[next]) continue;
				que.add(next);
				visit[next] = true;
				cnt++;
			}
		}
		
		return cnt;
	}

}
