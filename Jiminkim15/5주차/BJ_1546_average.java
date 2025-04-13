import javax.crypto.spec.PSource;
import java.util.Scanner;

public class BJ_1546_average {
    public static void main(String[] args) {
        Scanner ac = new Scanner(System.in);
        int N = ac.nextInt();
        int A[] = new int[N];
        for(int i=0; i<N; i++){
            A[i] = ac.nextInt();
        }

        long max = 0;
        long sum = 0;
        for (int i = 0; i < N; i++) {
            if(A[i] > max) max = A[i];
            sum = sum + A[i];
        }
        System.out.println(sum*100.0 / max / N);
    }

}
