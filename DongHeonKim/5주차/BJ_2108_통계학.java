import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] nums = new int[N];
        int[] freq = new int[8001];
        int sum = 0;

        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
            sum += nums[i];
            freq[nums[i] + 4000]++;
        }

        Arrays.sort(nums);

        // 1. 산술 평균
        double avg = (double) sum / N;
        System.out.println(Math.round(avg));

        // 2. 중앙값
        System.out.println(nums[N / 2]);

        // 3. 최빈값
        int maxFreq = 0;
        for (int i = 0; i < 8001; i++) {
            if (freq[i] > maxFreq) {
                maxFreq = freq[i];
            }
        }

        List<Integer> modeList = new ArrayList<>();
        for (int i = 0; i < 8001; i++) {
            if (freq[i] == maxFreq) {
                modeList.add(i - 4000); // 다시 원래 값으로 복원
            }
        }

        if (modeList.size() > 1) {
            Collections.sort(modeList);
            System.out.println(modeList.get(1));
        } else {
            System.out.println(modeList.get(0));
        }

        int range = nums[N - 1] - nums[0];
        System.out.println(range);
    }
}
