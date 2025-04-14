import java.io.*;
import java.util.*;

public class BJ_1495_기타리스트 {

  public static void main(String[] args) throws Exception {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    String[] input = br.readLine().split(" ");
    int N = Integer.parseInt(input[0]);
    int S = Integer.parseInt(input[1]);
    int M = Integer.parseInt(input[2]);

    int[] arr = new int[M + 1];  // arr[i]=n n번째 노래 순서일때 i크기의 볼륨

    for (int i = 0; i <= M; i++) {
      arr[i] = -1;
    }
    arr[S] = 0;

    input = br.readLine().split(" ");

    for (int i = 1; i <= N; i++) { // i는 첫번째부터 N번까지의 노래부를 순서를 나타낸다.
      int V = Integer.parseInt(input[i - 1]);
      List<Integer> list = new ArrayList<>();

      for (int j = 0; j <= M; j++) { // j는 0번째부터 M번(볼륨의 최대크기)까지의 볼륨을 나타낸다.
        if (arr[j] == i - 1) { // i-1번째에서 가능한 볼륨들을 찾는다.
          int res1 = j + V;
          int res2 = j - V;

          if (0 <= res1 && res1 <= M)
            list.add(res1); // 즉시 변경하지 않고 리스트에 모으는 이유는 이번 for문 동안 즉시 변경의 영향을 주지 않기 위해서이다.
          if (0 <= res2 && res2 <= M)
            list.add(res2);
        }
      }

      for (int n : list) {
        arr[n] = i;
      }
    }

    int max = -1;

    // 반복문을 돌면서 N번째 중에서 볼륨이 가장 큰 요소를 찾는다.
    for (int i = 0; i <= M; i++) {
      if (arr[i] == N)
        max = Math.max(max, i);
    }

    bw.write(max + "\n");
    bw.flush();

  }
}