import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1248_Guess {
    public static int N;
    public static char[][] sign;	//부호 저장 2차원 배열
    public static int[] result;		//수열 값 저장 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //입력값 처리하는 BufferedReader
        //------입력값 저장 및 배열 초기화--------
        N = Integer.parseInt(br.readLine());
        sign = new char[N][N];
        result = new int[N];
        String str = br.readLine();
        int index = 0;
        for(int i=0;i<N;i++) {
            for(int j=i;j<N;j++) {
                sign[i][j] = str.charAt(index++);
            }
        }
        guess(0);		//함수 실행
    }
    public static void guess(int depth) {
        if(depth==N) {
            for(int i=0;i<N;i++) {
                System.out.print(result[i] + " ");		//수열 출력
            }
            System.exit(0);		// 실행 종료
        }
        for(int i=-10;i<=10;i++) {
            result[depth] = i;	//수열에 값 추가
            if(signCheck(depth))	//부호 조건 확인
                guess(depth+1);		//부호 조건 만족시 재귀 진행
        }
        return;
    }
    //------수열이 부호에 만족하는지 확인하는 함수-------
    public static boolean signCheck(int depth) {
        int temp = 0;
        for(int i=0;i<depth+1;i++) {
            temp += result[i];		//현재 수열 값 모두 더하기
        }
        for(int i=0;i<depth+1;i++) {
            if((temp>0 && sign[i][depth]!='+') 	//조건 만족 안할 시
                    || (temp==0 && sign[i][depth]!='0')
                    || (temp<0 && sign[i][depth]!='-'))
                return false;
            temp -= result[i];	//다음 행은 범위가 좁아지기 때문에 빼기 진행
        }
        return true;
    }
}
