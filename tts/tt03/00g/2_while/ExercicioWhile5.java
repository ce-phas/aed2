import java.util.*;

public class ExercicioWhile5 {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int N = sc.nextInt();
        int grade = -1;
        int i = 0;
        int sum = 0;
        double avg = N * 0.6;
        double gradeA = N * 0.9;
        int underAvg = 0;
        int gradeACount = 0;

        while (i < 20) {
            while (grade < 0 || grade > N) {
                grade = sc.nextInt();
            }

            sum += grade;

            if (grade < avg) {
                underAvg++;
            } else if (grade > gradeA) {
                gradeACount++;
            }

            i++;
            grade = -1;
        }

        System.out.println("Média: " + (double) sum / 20);
        System.out.println("< 60%: " + underAvg);
        System.out.println("> 90%: " + gradeACount);
    }
}
