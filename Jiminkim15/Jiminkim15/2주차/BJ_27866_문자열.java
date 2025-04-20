import java.util.Scanner;

public class BJ_27866_문자열 {
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);

    String st = sc.next();
    int n = sc.nextInt()-1;

    System.out.println(st.charAt(n));

    sc.close();
  }
}