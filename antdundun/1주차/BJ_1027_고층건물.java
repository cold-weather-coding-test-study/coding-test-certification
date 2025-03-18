import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class BJ_1027_고층건물 {

    static int N, max;
    static long[] bd;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        bd = new long[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < N; i++) {
			bd[i] = Integer.parseInt(st.nextToken());
		}
        
        for (int i = 0; i < N; i++) {
        	int sum = find(i);
			
			max = Math.max(max, sum);
		}
        
        System.out.println(max);
    }
    
    private static int find(int idx) {
        int cnt = 0;
        double slope = 0;
        
        // 왼쪽
        for (int i = idx-1; i >= 0; i--) {
            double tmp = (double) (bd[idx]-bd[i]) / (idx-i);
           
            if (i == idx-1 || slope > tmp) {
                cnt++;
                slope = tmp;
            }
        }
        
        // 오른쪽
        for (int i = idx+1; i < N; i++) {
            double tmp = (double) (bd[idx] - bd[i]) / (idx-i);
            
            if (i==idx+1 || slope < tmp) {
                cnt++;
                slope = tmp;
            }
        }
        
        return cnt;
    }
}
