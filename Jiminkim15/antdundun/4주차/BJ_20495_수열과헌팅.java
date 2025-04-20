import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_20495_수열과헌팅 {
	
	static int N;
	static List<Node> num;
	static List<Long> minArr, maxArr;
	static class Node{
		int idx;
		long min;
		long max;
		public Node(int idx, long min, long max) {
			super();
			this.idx = idx;
			this.min = min;
			this.max = max;
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        
        num = new ArrayList<>();
        minArr = new ArrayList<>();
        maxArr = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			
			num.add(new Node(i, a-b, a+b));
			
			minArr.add(a-b);
			maxArr.add(a+b);
		}
        
        Collections.sort(minArr);
        Collections.sort(maxArr);
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
			Node cur = num.get(i);
			
			int minIdx = lowerBound(cur.min);
			int maxIdx = upperBound(cur.max);
			
			sb.append((minIdx+1)+" "+maxIdx+"\n");
		}
        
        System.out.println(sb.toString());
    }

	private static int upperBound(long max) {
		int lo = 0;
		int hi = N;
		
		while (lo < hi) {
			int mid = (lo+hi)/2;
			
			if (minArr.get(mid) <= max) {
				lo = mid+1;
			} else {
				hi = mid;
			}
		}
		
		return hi;
	}

	private static int lowerBound(long min) {
		int lo = 0;
		int hi = N;
		
		while (lo < hi) {
			int mid = (lo+hi)/2;
			
			if (maxArr.get(mid) < min) {
				lo = mid+1;
			} else {
				hi = mid;
			}
		}
		
		return hi;
	}
}
