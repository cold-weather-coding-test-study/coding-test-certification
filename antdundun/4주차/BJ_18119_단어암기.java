import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_18119_단어암기 {
	
	static int N, M;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        boolean[][] words = new boolean[N][26];
        boolean[] read = new boolean[N];
        int[] count = new int[N];
        
        for (int i = 0; i < N; i++) {
			String str = br.readLine();
			read[i] = true;
			
			for (int j = 0; j < str.length(); j++) {
				int alpha = str.charAt(j)-'a';
				words[i][alpha] = true;
			}
		}
        

        StringBuilder sb = new StringBuilder();
        
        int ans = N;
        for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int o = Integer.parseInt(st.nextToken());
			int x = st.nextToken().charAt(0)-'a';
			
			
			if (o==1) {
				for (int j = 0; j < N; j++) {
					if (words[j][x]) {
						count[j]++;
						if (read[j]) {
							ans--;
							read[j] = false;
						}
						
					}
				}
			} else {
				for (int j = 0; j < N; j++) {
					if (!read[j] && words[j][x]) {
						count[j]--;
						
						if (count[j]==0) {
							read[j] = true;
							ans++;
						}
					}
				}
			}

			sb.append(ans+"\n");
		}
        
        System.out.println(sb.toString());
        
    }
}
