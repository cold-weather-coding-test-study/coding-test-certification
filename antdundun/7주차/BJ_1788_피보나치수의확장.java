import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_1788_피보나치수의확장 {
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        
        if (N == 0) {
            System.out.println(0 + "\n" + 0);
            return;
        }
        
        if (N > 0) {
            sb.append(1);
        } else if (N < 0) {
            if ((-N) % 2 == 0) {
                sb.append(-1);
            } else {
                sb.append(1);
            }
        }
        
        sb.append("\n");
        
        N = Math.abs(N);
        long[] fib = new long[N+1];
        fib[1] = 1;
        
        for (int i = 2; i < N+1; i++) {
			fib[i] = (fib[i-2]+fib[i-1])%1000000000;
		}
        
        sb.append(fib[N]);
        
        System.out.println(sb.toString());
        
    }

}
