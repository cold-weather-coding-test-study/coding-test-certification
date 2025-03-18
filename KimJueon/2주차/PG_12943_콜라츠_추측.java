class PG_12943_콜라츠_추측 {
    public int solution(int num) {
        if(num == 1) return 0;

        int count = 0;

        while(count < 500) {
            if(num == 1) return count;

            if(num % 2 == 0) {
                num /= 2;
            }
            else if(num % 2 == 1) {
                num = num * 3 + 1;
            }

            count++;
        }

        return -1;
    }
}