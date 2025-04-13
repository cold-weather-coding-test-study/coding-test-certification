import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_11659_PartSum {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(System.in));//받는 데이터가 많을 경우 buffer사용 scan보다 더 빠름
        StringTokenizer stringTokenizer =
                new StringTokenizer(bufferedReader.readLine() /*예제 첫번쨰줄을 읽어옴*/);//숫자의 개수가 10만개면 한줄에 길게 옆으로 데이터가 들어오게 됨 이 경우는 int형으로 받기가 힘듬 이럴때 stringtokenizer를 사용해서 값을 분리함

        int suNo = Integer.parseInt(stringTokenizer.nextToken());
        int quizNo = Integer.parseInt(stringTokenizer.nextToken());
        long[]S = new long[suNo + 1]; //long형으로 쓴 이유는 덧셈이나 곱셈이 많을 때 int형 범위를 넘을 수도 있음
        stringTokenizer =
                new StringTokenizer(bufferedReader.readLine());
        for (int i = 1; i < suNo ; i++) {
            S[i] = S[i-1] + Integer.parseInt(stringTokenizer.nextToken());
        }

        for (int q = 0; q < quizNo; q++) {
            stringTokenizer =
                    new StringTokenizer(bufferedReader.readLine());
            int i = Integer.parseInt(stringTokenizer.nextToken());
            int j = Integer.parseInt(stringTokenizer.nextToken());
            System.out.println(S[j] - S[i]);
        }
    }
}
