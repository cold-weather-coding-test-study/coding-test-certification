
import java.io.*;
import java.util.*;

public class BJ_13975_파일_합치기_3 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		PriorityQueue<Long> queue = new PriorityQueue<>();
		long result = 0;

		int t = Integer.parseInt(bf.readLine());
		for (int i = 0; i < t; i++) {
			int k = Integer.parseInt(bf.readLine());
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < k; j++) {
				queue.add(Long.parseLong(st.nextToken()));
			}

			while (!queue.isEmpty()) {
				long a = queue.poll();
				long b = queue.poll();
				result += (a + b);
				if (queue.size() == 0)
					break;
				queue.add(a + b);
			}
			bw.write(result + "\n");
			result = 0;
		}
		bw.flush();
	}
}