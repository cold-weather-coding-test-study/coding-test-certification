import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_13975_파일합치기3 {
	
	static int T, K;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        T = Integer.parseInt(br.readLine());
        
        PriorityQueue<Long> pq = new PriorityQueue<>();
        
        for (int t = 0; t < T; t++) {
			K = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < K; i++) {
				long cd = Long.parseLong(st.nextToken());
				pq.add(cd);
			}
			
			long sum = 0;
			while (pq.size() > 1) {
				long first = pq.poll();
				long second = pq.poll();
				
				pq.add(first+second);
				
				sum += (first+second);
			}
			
			pq.poll();
			System.out.println(sum);
		}
    }

}
