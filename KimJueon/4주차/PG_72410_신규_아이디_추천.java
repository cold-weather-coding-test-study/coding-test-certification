public class PG_72410_신규_아이디_추천 {

    public String solution(String new_id) {
        String answer = new_id.toLowerCase();

        answer = answer.replaceAll("[^a-z0-9-_.]", "");

        while (answer.contains("..")) {
            answer = answer.replace("..", ".");
        }

        if (answer.charAt(0) == '.') {
            answer = answer.substring(1);
        }
        if (answer.length() > 0 && answer.charAt(answer.length() - 1) == '.') {
            answer = answer.substring(0, answer.length() - 1);
        }

        if (answer.length() == 0) answer = "a";

        if (answer.length() > 15) {
            answer = answer.substring(0, 15);
            if (answer.charAt(answer.length() - 1) == '.') {
                answer = answer.substring(0, answer.length() - 1);
            }
        }

        if (answer.length() == 1) {
            answer += String.valueOf(answer.charAt(0)) + String.valueOf(answer.charAt(0));
        } else if (answer.length() == 2) {
            answer += String.valueOf(answer.charAt(1));
        }

        return answer;
    }
}
