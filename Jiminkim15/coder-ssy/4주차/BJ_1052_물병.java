import java.io.*;

public class BJ_1052_물병 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] input = br.readLine().split(" ");
    int N = Integer.parseInt(input[0]);
    int K = Integer.parseInt(input[1]);

    int answer = 0;
    int temp = N;

    while (Integer.bitCount(temp) > K) {
      // 가장 낮은 비트만큼 추가
      int lowestBit = temp & -temp;
      answer += lowestBit;
      temp += lowestBit;
    }

    System.out.println(answer);
  }
}
