import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of grades: ");
        int numGrades = scanner.nextInt();

        if (numGrades <= 0) {
            System.out.println("Invalid number of grades! Please enter a positive number.");
            return;
        }

        double[] grades = new double[numGrades];
        double sum = 0;

        for (int i = 0; i < numGrades; i++) {
            System.out.print("Enter grade " + (i + 1) + ": ");
            grades[i] = scanner.nextDouble();
            sum += grades[i];
        }

        double average = sum / numGrades;
        System.out.println("\nAverage Grade: " + average);

        if (average >= 90) {
            System.out.println("Grade: A (Excellent!)");
        } else if (average >= 80) {
            System.out.println("Grade: B (Good job!)");
        } else if (average >= 70) {
            System.out.println("Grade: C (Fair performance!)");
        } else if (average >= 60) {
            System.out.println("Grade: D (Needs improvement!)");
        } else {
            System.out.println("Grade: F (Fail - Study harder!)");
        }

        scanner.close();
    }
}