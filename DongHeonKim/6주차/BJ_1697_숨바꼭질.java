import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 100000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        // dist[x] : N에서 x까지 이동하는 최단 시간
        int[] dist = new int[MAX + 1];
        Arrays.fill(dist, -1);
        
        Queue<Integer> queue = new LinkedList<>();
        
        dist[N] = 0;
        queue.offer(N);
        
        while(!queue.isEmpty()) {
            int x = queue.poll();
            

            if(x == K) {
                System.out.println(dist[x]);
                return;
            }
            
            if(x - 1 >= 0 && dist[x - 1] == -1) {
                dist[x - 1] = dist[x] + 1;
                queue.offer(x - 1);
            }

            if(x + 1 <= MAX && dist[x + 1] == -1) {
                dist[x + 1] = dist[x] + 1;
                queue.offer(x + 1);
            }

            if(2 * x <= MAX && dist[2 * x] == -1) {
                dist[2 * x] = dist[x] + 1;
                queue.offer(2 * x);
            }
        }
    }
}
