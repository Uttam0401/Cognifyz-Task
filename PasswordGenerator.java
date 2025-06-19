import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter desired password length: ");
        int length = scanner.nextInt();
        scanner.nextLine();

        if (length <= 0) {
            System.out.println("Error: Password length must be greater than 0.");
            return;
        }

        boolean includeNumbers = getUserChoice(scanner, "Include numbers? (Y/N): ");
        boolean includeUppercase = getUserChoice(scanner, "Include uppercase letters? (Y/N): ");
        boolean includeLowercase = getUserChoice(scanner, "Include lowercase letters? (Y/N): ");
        boolean includeSpecialChars = getUserChoice(scanner, "Include special characters? (Y/N): ");

        String password = generatePassword(length, includeNumbers, includeUppercase, includeLowercase, includeSpecialChars);
        System.out.println("\nGenerated Password: " + password);

        scanner.close();
    }

    private static boolean getUserChoice(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine().trim().equalsIgnoreCase("Y");
    }

    private static String generatePassword(int length, boolean includeNumbers, boolean includeUppercase,
                                           boolean includeLowercase, boolean includeSpecialChars) {
        String numbers = "0123456789";
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String specialChars = "!@#$%^&*()-_=+<>?/";

        StringBuilder charPool = new StringBuilder();
        if (includeNumbers) charPool.append(numbers);
        if (includeUppercase) charPool.append(uppercase);
        if (includeLowercase) charPool.append(lowercase);
        if (includeSpecialChars) charPool.append(specialChars);

        if (charPool.length() == 0) return "Error: No character set selected!";

        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            password.append(charPool.charAt(random.nextInt(charPool.length())));
        }
        return password.toString();
    }
}