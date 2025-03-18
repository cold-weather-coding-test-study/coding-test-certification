import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_2539_모자이크 {
	
    static int N, M;
    static int paper, block;
    static List<Node> list;
    
    static class Node implements Comparable<Node>{
    	int r;
    	int c;
    	public Node(int r, int c) {
    		this.r = r;
    		this.c = c;
    	}
		@Override
		public int compareTo(Node o) {
			return this.c-o.c;
		}
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        paper = Integer.parseInt(br.readLine());
        block = Integer.parseInt(br.readLine());
        
        list = new ArrayList<>();
        
        for (int i = 0; i < block; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			list.add(new Node(r, c));
		}
        
        Collections.sort(list);
        
        int min = Math.max(N, M);
        int lo = 1;
        int hi = Math.max(N, M);
        
        while (lo <= hi) {
        	int mid = (lo+hi)/2;
        	
        	int cnt = 1;
        	int idx = list.get(0).c;
        	boolean flag = true;
        	
        	for (int i = 1; i < list.size(); i++) {
				Node cur = list.get(i);
				
				if (cur.r > mid) {
					flag = false;
					break;
				}
				
				if (idx < cur.c && cur.c-idx >= mid) {
					idx = cur.c;
					cnt++;
				}
			}
        	
        	if (cnt > paper) flag = false;
        	
        	if (flag) {
        		min = Math.min(min, mid);
        		hi = mid-1;
        	} else {
        		lo = mid+1;
        	}
        }
        
        System.out.println(min);
    }
    
}
