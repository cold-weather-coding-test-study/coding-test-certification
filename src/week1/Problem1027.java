package week1;

import java.util.Scanner;

public class Problem1027 {
    public static void main(String[] args) {
        final int nMax = 50;
        final int hMax = 1000000000;

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int h = sc.nextInt();

        sc.close();

        if (n > nMax || h > hMax)
            throw new RuntimeException("input Error");

    }
    
}
