import java.io.*;
import java.util.Scanner;

public class FileEncryptDecrypt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("File Encryption/Decryption");
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();

        System.out.print("Enter output file path: ");
        String outputFilePath = scanner.nextLine();

        System.out.print("Enter shift key (integer value): ");
        int shiftKey = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Choose operation (E for Encrypt / D for Decrypt): ");
        String operation = scanner.nextLine().trim().toUpperCase();

        if (!operation.equals("E") && !operation.equals("D")) {
            System.out.println("Invalid operation! Use 'E' for encryption or 'D' for decryption.");
            return;
        }

        processFile(filePath, outputFilePath, shiftKey, operation.equals("E"));
        System.out.println("Operation completed! Check: " + outputFilePath);
        scanner.close();
    }

    private static void processFile(String inputFile, String outputFile, int shift, boolean encrypt) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            int adjustedShift = encrypt ? shift : -shift;

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(applyCaesarCipher(line, adjustedShift));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static String applyCaesarCipher(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : (Character.isLowerCase(ch) ? 'a' : '0');
                int range = Character.isDigit(ch) ? 10 : 26;
                result.append((char) (base + (ch - base + shift + range) % range));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
}