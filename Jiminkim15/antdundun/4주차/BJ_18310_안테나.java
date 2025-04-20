import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_18310_안테나 {
	
	static int N;
	static int[] an;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        an = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
			an[i] = Integer.parseInt(st.nextToken());
		}
        
        Arrays.sort(an);
        
        System.out.println(an[(N-1)/2]);
    }
}
