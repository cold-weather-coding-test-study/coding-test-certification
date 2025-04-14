import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_1248_Guess {
	
	static int N, len;
	static char[][] map;
	static int[] ans;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        char[] seq = br.readLine().toCharArray();
        
        ans = new int[N];
        map = new char[N][N];
        
        int idx = 0;
        for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				map[i][j] = seq[idx++];
			}
		}
        
        perm(0);
        
    }

	private static void perm(int depth) {		
		if (depth == N) {
			for (int i = 0; i < N; i++) {
				System.out.print(ans[i]+" ");
			}
			
			System.exit(0);
			return;
		}
		
		for (int i = -10; i < 11; i++) {
			ans[depth] = i;
			if (check(depth+1)) {
				perm(depth+1);
			}
		}
	}

	private static boolean check(int idx) {
		for (int i = 0; i < idx; i++) {
			int sum = 0;
			for (int j = i; j < idx; j++) {
				sum += ans[j];
				if (sum > 0 && map[i][j] != '+') return false;
				if (sum < 0 && map[i][j] != '-') return false;
				if (sum==0 && map[i][j] != '0') return false;
			}
		}
		
		return true;
	}

}
