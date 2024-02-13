// The ConnectFour class implements AbstractStrategyGame and defines its logic for connect four
import java.util.*;

public class ConnectFour implements AbstractStrategyGame {

    // 2D array representing the game board
    private char[][] board;
    
    // Integer to track the current player (1 for 'X', 2 for 'O')
    private int currentPlayer;

    // Constructor that initializes the game board and sets the current player
    public ConnectFour() {
        board = new char[7][6];
        currentPlayer = 1;

        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 6; row++) {
                board[col][row] = '-';
            }
        }
    }

    // Method to determine if the game is over (either by win or draw)
    public boolean isGameOver() {
        return getWinner() >= 0;
    }

    // Method to determine the winner of the game
    // Returns 1 or 2 for the respective player, -1 for no winner yet, and 0 for a draw.
    public int getWinner() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j++) { 
                if (board[i][j] == 'X' && board[i][j + 1] == 'X'
                        && board[i][j + 2] == 'X' && board[i][j + 3] == 'X') {
                    return 1;
                }
                if (board[i][j] == 'O' && board[i][j + 1] == 'O'
                        && board[i][j + 2] == 'O' && board[i][j + 3] == 'O') {
                    return 2;
                }
            }
        }

        for (int i = 0; i < 6; i++) { 
            for (int j = 0; j < 3; j++) { 
                if (board[j][i] == 'X' && board[j + 1][i] == 'X'
                        && board[j + 2][i] == 'X' && board[j + 3][i] == 'X') {
                    return 1;
                }
                if (board[j][i] == 'O' && board[j + 1][i] == 'O'
                        && board[j + 2][i] == 'O' && board[j + 3][i] == 'O') {
                    return 2;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 'X' && board[i + 1][j + 1] == 'X'
                        && board[i + 2][j + 2] == 'X' && board[i + 3][j + 3] == 'X') {
                    return 1;
                }
                if (board[i][j] == 'O' && board[i + 1][j + 1] == 'O'
                        && board[i + 2][j + 2] == 'O' && board[i + 3][j + 3] == 'O') {
                    return 2;
                }

                if (board[i + 3][j] == 'X' && board[i + 2][j + 1] == 'X'
                        && board[i + 1][j + 2] == 'X' && board[i][j + 3] == 'X') {
                    return 1;
                }
                if (board[i + 3][j] == 'O' && board[i + 2][j + 1] == 'O'
                        && board[i + 1][j + 2] == 'O' && board[i][j + 3] == 'O') {
                    return 2;
                }
            }
        }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (board[i][j] == '-') {
                    return -1;
                }
            }
        }
        return 0;
    }

    // Method to get the next player
    // If the game is over, it returns -1.
    public int getNextPlayer() {
        if (isGameOver()) {
            return -1;
        } else {
            if (currentPlayer == 1) {
                return 1;
            } else {
                return 2;
            }
        }
    }      

    // Prompts the current player to make a move by entering a column number
    // Parameters;
    //            - Scanner input of the user, for the decision
    public void makeMove(Scanner input) {
        char currPlayer;
        if (currentPlayer == 1) {
            currPlayer = 'X';
        } else {
            currPlayer = 'O';
    }

    System.out.print("Column (0-6)? ");
    int column = input.nextInt();

    if (isValidMove(column)) {
        for (int row = 5; row >= 0; row--) {
            if (board[column][row] == '-') {
                board[column][row] = currPlayer;
                currentPlayer = 3 - currentPlayer;
                return;
            }
        }
    } else {
        throw new IllegalArgumentException("Invalid move. Please choose a valid column\n"
        + "(0-6).");
    }
}

    // Provides the game instructions as a string
    public String instructions() {
        return "Connect Four Rules:\n"
                + "1. Players take turns dropping their tokens in one of the columns.\n"
                + "2. The token will fall to the bottom-most available space in the\n" 
                + "chosen column.\n"
                + "3. The first player to connect four tokens in a row (horizontally,\n"
                + "  vertically, or diagonally) wins!\n"
                + "4. To make a move, enter the column number (0-6) where you want to\n"
                + " drop your token.\n";
    }

    // Returns a string representation of the game board
    public String toString() {
        String result = "";
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                result += board[col][row] + " ";
            }
            result += "\n";
        }
        return result;
    }

    // Checks if a move to a specified column is valid (within the board and not full)
    private boolean isValidMove(int column) {
        return column >= 0 && column < 7 && board[column][0] == '-';
    }
}
