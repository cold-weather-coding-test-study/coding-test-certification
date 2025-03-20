package DongHeonKim.week2;

import java.io.*;
import java.util.*;

public class BJ_2539_모자이크 {
    static int rows, columns;                 // 도화지의 크기 (세로 x 가로)
    static int usePaper;                      // 사용할 수 있는 색종이의 개수
    static int wrongPaper;                    // 잘못 칠해진 칸 개수
    static ArrayList<int[]> points = new ArrayList<>();  // 잘못 칠해진 칸들의 좌표 저장 (y, x)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        rows = Integer.parseInt(st.nextToken());
        columns = Integer.parseInt(st.nextToken());

        usePaper = Integer.parseInt(br.readLine().trim());
        wrongPaper = Integer.parseInt(br.readLine().trim());

        int maxY = 0;  // 잘못 칠해진 칸 중 최대 y좌표 저장용

        // 잘못 칠해진 칸 좌표 입력받기
        for (int i = 0; i < wrongPaper; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            points.add(new int[]{y, x});
            maxY = Math.max(maxY, y); // 세로 길이의 최소값을 찾기 위한 최대 y좌표 업데이트
        }

        // 세로 길이를 이분 탐색으로 최적화하여 최소값 찾기
        int low = maxY;        // 최소 높이 (잘못 칠해진 최대 y값)
        int high = rows;       // 최대 높이 (도화지 전체 높이)
        int result = rows;     // 최종 결과값 (최소 높이를 저장할 변수)

        while (low <= high) {
            int mid = (low + high) / 2; // 중간값(현재 시도할 색종이 세로 길이)

            if (isPossible(mid)) {      // 주어진 높이로 모든 칸 덮기가 가능하면
                result = mid;           // 일단 현재 높이 저장
                high = mid - 1;         // 높이를 줄여서 다시 탐색
            } else {
                low = mid + 1;          // 불가능하면 높이를 늘려서 다시 탐색
            }
        }

        System.out.println(result);     // 최소 높이 출력
    }

    // 주어진 세로 길이(givenHeight)로 색종이를 사용했을 때, 주어진 개수(usePaper) 내로 가능한지 체크
    static boolean isPossible(int givenHeight) {
        List<Integer> xList = new ArrayList<>(); // 주어진 높이 이하의 점들의 x좌표를 모으기 위한 리스트

        for (int[] p : points) {
            if (p[0] <= givenHeight)
                xList.add(p[1]);     // 높이 내의 점이면 x좌표를 리스트에 추가
            else
                return false;        // 주어진 높이를 초과하는 점이 있으면 이 높이는 불가능
        }

        // x 좌표를 기준으로 정렬하여 가로 방향으로 색종이를 붙일 준비
        Collections.sort(xList);

        int cnt = 1;    // 사용한 색종이 개수 (최소 1개부터 시작)
        int startX = xList.get(0);     // 첫 번째 색종이의 시작 x좌표

        for (int x : xList) {
            // 현재 색종이가 덮을 수 있는 범위를 벗어나면 새로운 색종이를 사용
            if (x > startX + columns - 1) {  // 현재 색종이로 덮을 수 없는 위치라면
                cnt++;                        // 색종이 한 장 추가
                startX = x;                   // 새 색종이 시작 위치 변경
            }
        }

        // 사용한 색종이 개수가 주어진 색종이 개수 이하면 true (성공)
        return cnt <= usePaper;
    }
}