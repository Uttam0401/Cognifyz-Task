import java.util.*;

public class PalindromeChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Palindrome Checker");
        System.out.println("Choose an option:");
        System.out.println("1. Check if a string is a palindrome");
        System.out.println("2. Check if a number is a palindrome");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                checkStringPalindrome(scanner);
                break;
            case 2:
                checkNumberPalindrome(scanner);
                break;
            default:
                System.out.println("Invalid choice! Please enter 1 or 2.");
        }

        scanner.close();
    }


    private static void checkStringPalindrome(Scanner scanner) {
        System.out.print("Enter a string: ");
        String input = scanner.nextLine().toLowerCase();
        String reversed = new StringBuilder(input).reverse().toString();

        if (input.equals(reversed)) {
            System.out.println("The string is a palindrome!");
        } else {
            System.out.println("The string is NOT a palindrome.");
        }
    }


    private static void checkNumberPalindrome(Scanner scanner) {
        System.out.print("Enter a number: ");
        int num = scanner.nextInt();
        int originalNum = num, reversedNum = 0;

        while (num > 0) {
            int digit = num % 10;
            reversedNum = reversedNum * 10 + digit;
            num /= 10;
        }

        if (originalNum == reversedNum) {
            System.out.println("The number is a palindrome!");
        } else {
            System.out.println("The number is NOT a palindrome.");
        }
    }
}