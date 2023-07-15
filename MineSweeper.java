import java.util.Scanner;

public class MineSweeper {

    private int[][] board;
    private int[] mines;
    private Scanner scanner;

    public MineSweeper(int rows, int columns) {
        board = new int[rows][columns];
        mines = new int[rows * columns];
        scanner = new Scanner(System.in);
    }

    public void generateMines() {
        int mineCount = board.length * board[0].length / 4;
        for (int i = 0; i < mineCount; i++) {
            int row = (int) (Math.random() * board.length);
            int column = (int) (Math.random() * board[0].length);
            mines[row * board[0].length + column] = 1;
        }
    }

    public void displayBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void play() {
        int row, column;
        while (true) {
            System.out.print("Row: ");
            row = scanner.nextInt() - 1;
            System.out.print("Column: ");
            column = scanner.nextInt() - 1;
            if (row < 0 || row >= board.length || column < 0 || column >= board[0].length) {
                System.out.println("Invalid input.");
                continue;
            }
            if (board[row][column] == 1) {
                System.out.println("Boom! You lost.");
                break;
            } else {
                int count = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (row + i >= 0 && row + i < board.length && column + j >= 0 && column + j < board[0].length) {
                            if (mines[row + i * board[0].length + column + j] == 1) {
                                count++;
                            }
                        }
                    }
                }
                board[row][column] = count;
            }
            if (checkWon()) {
                System.out.println("Congratulations! You won.");
                break;
            }
            displayBoard();
        }
    }

    public boolean checkWon() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int rows, columns;
        System.out.print("Enter the number of rows: ");
        rows = scanner.nextInt();
        System.out.print("Enter the number of columns: ");
        columns = scanner.nextInt();
        MineSweeper game = new MineSweeper(rows, columns);
        game.generateMines();
        game.play();
    }
}