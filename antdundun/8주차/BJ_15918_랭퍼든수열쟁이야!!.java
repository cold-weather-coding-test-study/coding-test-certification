import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_15918_랭퍼든수열쟁이야!! {

    static int n, x, y, ans;
    static int[] arr;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        
        arr = new int[2*n+1];
        visit = new boolean[n+1];
        
        int diff = y-x-1;
        
        arr[x] = arr[y] = diff;
        visit[diff] = true;
        
        ans = 0;
        backtracking(1);
        
        System.out.println(ans);
    }
    
    private static void backtracking(int idx) {
    	if (idx == 2*n) {
    		ans++;
    		return;
    	}
    	
    	if (arr[idx]==0) {
    		for (int i = 1; i < n+1; i++) {
				if (visit[i]) continue;
				if (idx+i+1 <= 2*n && arr[idx+i+1]==0) {
					visit[i] = true;
					arr[idx] = arr[idx+i+1] = i;
					backtracking(idx+1);
					arr[idx] = arr[idx+i+1] = 0;
					visit[i] = false;
				}
			}
    	} else {
    		backtracking(idx+1);
    	}
    }

}
