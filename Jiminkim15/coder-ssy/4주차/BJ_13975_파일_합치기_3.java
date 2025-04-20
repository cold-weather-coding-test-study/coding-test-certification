import java.io.*;
import java.util.*;

public class BJ_13975_파일_합치기_3 {
  public static void main(String[] args) throws IOException {
    // 빠른 입력을 위한 BufferedReader
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 출력 최적화를 위한 BufferedWriter
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

    while (T-- > 0) {
      int K = Integer.parseInt(br.readLine()); // 파일 수

      PriorityQueue<Integer> pq = new PriorityQueue<>();
      StringTokenizer st = new StringTokenizer(br.readLine());

      // 파일 크기들을 우선순위 큐에 삽입
      for (int i = 0; i < K; i++) {
        pq.add(Integer.parseInt(st.nextToken()));
      }

      int totalCost = 0;

      // 가장 작은 2개의 파일을 계속 합쳐가며 최소 비용 계산
      while (pq.size() > 1) {
        int first = pq.poll();
        int second = pq.poll();
        int cost = first + second;
        totalCost += cost;
        pq.add(cost);
      }

      bw.write(totalCost + "\n");
    }

    bw.flush();
    bw.close();
    br.close();
  }
}
