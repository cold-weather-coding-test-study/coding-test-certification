	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.PriorityQueue;
	import java.util.StringTokenizer;
	public class boj_13975_파일합치기3 {

		public static void main(String[] args) throws NumberFormatException, IOException {

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = null;
			StringBuffer sb = new StringBuffer();
			int T = Integer.parseInt(br.readLine());
			for(int tc=1; tc<=T; tc++) {
				PriorityQueue<Long > pq = new PriorityQueue<Long>();
				int K = Integer.parseInt(br.readLine());
				st = new StringTokenizer(br.readLine());
				while(st.hasMoreTokens()) {
					pq.add(Long.parseLong(st.nextToken()));
				}
				Long sum=(long) 0;
				while(pq.size()>1) {
					Long a = pq.poll();
					Long b = pq.poll();
					sum += a+b;
					pq.add(a+b);
				}
				sb.append(sum);
				sb.append("\n");
			}
			System.out.println(sb.toString());
		}
	}