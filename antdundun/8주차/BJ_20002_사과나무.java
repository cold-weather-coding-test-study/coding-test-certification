import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_20002_사과나무 {

    static int N;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        
        map = new int[N][N];
        
        for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int num = Integer.parseInt(st.nextToken());
				if (r==0 && c==0) {
					map[r][c] = num;
				} else if (r==0 && c > 0) {
					map[r][c] = num + map[r][c-1];
				} else if (r > 0 && c==0) {
					map[r][c] = num + map[r-1][c];
				} else {
					map[r][c] = num + map[r-1][c] + map[r][c-1] - map[r-1][c-1];					
				}
			}
		}
        
        int K = 0;
        int max = -1000;
        
        while (K < N) {
        	for (int r = K; r < N; r++) {
				for (int c = K; c < N; c++) {
					if (r==K && c==K) {
						max = Math.max(max, map[r][c]);
					} else if (r==K && c > K) {
						max = Math.max(max, map[r][c]-map[r][c-K-1]);
					} else if (r > K && c==K) {
						max = Math.max(max, map[r][c]-map[r-K-1][c]);
					} else {
						max = Math.max(max, map[r][c]-map[r-K-1][c]-map[r][c-K-1]+map[r-K-1][c-K-1]);
					}
				}
			}
        	
        	K++;
        }
        
        System.out.println(max);
         
    }

}
