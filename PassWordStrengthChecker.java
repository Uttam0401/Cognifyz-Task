import java.util.Scanner;

public class PassWordStrengthChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        String strength = evaluatePassword(password);
        System.out.println("\nPassword Strength: " + strength);

        scanner.close();
    }

    private static String evaluatePassword(String password) {
        int length = password.length();
        boolean hasUppercase = password.matches(".*[A-Z].*");
        boolean hasLowercase = password.matches(".*[a-z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()-_=+<>?/].*");

        int score = (hasUppercase ? 1 : 0) + (hasLowercase ? 1 : 0) + (hasNumber ? 1 : 0) + (hasSpecial ? 1 : 0);

        if (length < 6) {
            return "Weak (Too short)";
        } else if (score == 1) {
            return "Weak";
        } else if (score == 2) {
            return "Moderate";
        } else if (score == 3) {
            return "Strong";
        } else {
            return "Very Strong";
        }
    }
}