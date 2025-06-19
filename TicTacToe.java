import java.util.Scanner;

public class TicTacToe {
    private static char[][] board;
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do {
            initializeBoard();
            char currentPlayer = PLAYER_X;
            boolean gameWon = false;
            int moves = 0;

            while (moves < 9 && !gameWon) {
                displayBoard();
                System.out.print("Player " + currentPlayer + ", enter your move (row and column, 1-3): ");
                int row = scanner.nextInt() - 1;
                int col = scanner.nextInt() - 1;

                if (isValidMove(row, col)) {
                    board[row][col] = currentPlayer;
                    moves++;
                    gameWon = checkWinner(currentPlayer);

                    if (!gameWon) {
                        currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
                    }
                } else {
                    System.out.println("Invalid move! Try again.");
                }
            }

            displayBoard();
            if (gameWon) {
                System.out.println("Player " + currentPlayer + " wins!");
            } else {
                System.out.println("It's a draw!");
            }

            System.out.print("Do you want to play again? (Y/N): ");
            playAgain = scanner.next().equalsIgnoreCase("Y");

        } while (playAgain);

        scanner.close();
    }

    private static void initializeBoard() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    private static void displayBoard() {
        System.out.println("\nCurrent Board:");
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-';
    }

    private static boolean checkWinner(char player) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) || 
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) || 
               (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }
}