class PG_81301_숫자_문자열과_영단어 {

    public int solution(String s) {
        String[] convert = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight",
            "nine"};

        for (int i = 0; i < convert.length; i++) {
            s = s.replaceAll(convert[i], Integer.toString(i));
        }

        return Integer.parseInt(s);
    }
}