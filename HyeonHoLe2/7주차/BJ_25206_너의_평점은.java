import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double totalScore = 0.0;
        double totalCredit = 0.0;

        for (int i = 0; i < 20; i++) {
            String line = sc.nextLine(); 
            StringTokenizer st = new StringTokenizer(line);

            String subject = st.nextToken();         
            double credit = Double.parseDouble(st.nextToken()); 
            String grade = st.nextToken();            

            if (grade.equals("P")) continue;

            double score = convertGradeToScore(grade);
            totalScore += credit * score;
            totalCredit += credit;
        }

        double result = totalScore / totalCredit;
        System.out.printf("%.6f\n", result);
    }

    public static double convertGradeToScore(String grade) {
        switch (grade) {
            case "A+": return 4.5;
            case "A0": return 4.0;
            case "B+": return 3.5;
            case "B0": return 3.0;
            case "C+": return 2.5;
            case "C0": return 2.0;
            case "D+": return 1.5;
            case "D0": return 1.0;
            case "F":  return 0.0;
            default:   return 0.0;
        }
    }
}
