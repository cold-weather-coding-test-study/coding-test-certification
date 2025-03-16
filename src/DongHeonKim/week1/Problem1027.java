package DongHeonKim.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem1027 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] buildingHeight = new int[N];
        for (int i = 0; i < N; i++) {
            buildingHeight[i] = Integer.parseInt(st.nextToken());
        }

        int maxCnt = 0;
        for (int i = 0; i < N; i++) {
            double leftHighInclination = -1000000000; //왼쪽 기울기 최대
            double rightHighInclination = -1000000000; //오른쪽 기울기 최대
            int leftCnt = 0; //왼쪽에 볼수 있는 빌딩 수
            int rightCnt =  0; //오른쪽에 볼수 있는 빌딩 수

            for (int j = i-1; j >=0; j--) {
                //기울기 (높이차이)/(i-j)
                double tempInclination = (double)(buildingHeight[j]-buildingHeight[i])/(i-j);
                if(tempInclination>leftHighInclination){
                    leftHighInclination = tempInclination;
                    leftCnt++;
                }
            }

            for (int j = i+1; j < N; j++) {
                //기울기 (높이차이)/(j-i)
                double tempInclination = (double)(buildingHeight[j]-buildingHeight[i])/(j-i);
                if(tempInclination>rightHighInclination){
                    rightHighInclination = tempInclination;
                    rightCnt++;
                }
            }
            if((rightCnt+leftCnt)>maxCnt) maxCnt = rightCnt+leftCnt;
        }
        System.out.println(maxCnt);
    }
    
}
