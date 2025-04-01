import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        for (int t = 0; t < T; t++) {
            int K = Integer.parseInt(br.readLine());
            PriorityQueue<Long> pq = new PriorityQueue<>();
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                pq.add(Long.parseLong(st.nextToken()));
            }
            
            long totalCost = 0;
            // 파일이 하나 남을 때까지 두 개씩 합치기
            while (pq.size() > 1) {
                long first = pq.poll();
                long second = pq.poll();
                long cost = first + second;
                totalCost += cost;
                pq.add(cost);
            }
            sb.append(totalCost).append("\n");
        }
        System.out.print(sb);
    }
}
