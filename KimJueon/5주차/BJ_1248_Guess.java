import java.util.Scanner;

public class BJ_1248_Guess {
  static int N;
  static String str;
  static char[] cArr;
  static int[][] buho;
  static int[] result;
  static int[] sum;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    sc.nextLine();
    str = sc.nextLine();
    result = new int[N];
    buho = new int[N][N];
    sum = new int[N+1];
    int strIdx = 0;
    for(int i = 0; i < N; i++) {
      for(int j = i; j < N; j++) {
        if(str.charAt((strIdx)) == '-')
          buho[i][j] = -1;
        else if(str.charAt(strIdx) == '0')
          buho[i][j] = 0;
        else if(str.charAt(strIdx) == '+')
          buho[i][j] = 1;
        strIdx++;
      }
    }
    solve(0);
    for(int i = 0; i < N; i++)
      System.out.print(result[i]+" ");
    sc.close();
  }

  static boolean solve(int index) {
    if(index == N)
      return true;

    for(int i = 0; i <= 20; i++) {
      result[index] = i-10;
      if(check(index) && solve(index+1))
        return true;
    }
    return false;
  }

  static boolean check(int index) {
    int sum = 0;
    for(int i = index; i >= 0; i--) {
      sum += result[i];
      if(buho[i][index] == 0 && sum != 0)
        return false;
      if(buho[i][index] < 0 && sum >= 0)
        return false;
      if(buho[i][index] > 0 && sum <= 0)
        return false;
    }
    return true;
  }
}
