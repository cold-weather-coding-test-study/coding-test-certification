import java.util.Scanner;

public class BJ_1546_average2 {
    public static void main(String[] args) {
        Scanner ac = new Scanner(System.in);
        int N = ac.nextInt();

        long sum = 0;
        long max = 0;

        for (int i = 0; i < N; i++) {
            int temp = ac.nextInt();
            if(temp>max) max = temp;
            sum += temp;

        }

        System.out.println(sum*100.0/max/N);
    }

}
