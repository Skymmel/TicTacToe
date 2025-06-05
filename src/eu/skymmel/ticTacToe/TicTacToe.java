package eu.skymmel.ticTacToe;

public class TicTacToe {
    public static void main(String[] args) {
        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) board[i][j] = ' ';
        java.util.Scanner sc = new java.util.Scanner(System.in);
        char player = 'X';
        while (true) {
            printBoard(board);
            System.out.println("Hraje " + player + ". Zadej souřadnici ve formátu rc (např. 02 pro řádek 0, sloupec 2):");
            String input = sc.next();
            if (input.length() != 2 || !Character.isDigit(input.charAt(0)) || !Character.isDigit(input.charAt(1))) {
                System.out.println("Neplatný formát!");
                continue;
            }
            int r = input.charAt(0) - '0';
            int c = input.charAt(1) - '0';
            if (r < 0 || r > 2 || c < 0 || c > 2 || board[r][c] != ' ') {
                System.out.println("Neplatný tah!");
                continue;
            }
            board[r][c] = player;
            if (win(board, player)) {
                printBoard(board);
                System.out.println("Vyhrál hráč " + player + "!");
                break;
            }
            if (full(board)) {
                printBoard(board);
                System.out.println("Remíza!");
                break;
            }
            player = (player == 'X') ? 'O' : 'X';
        }
    }
    private static void printBoard(char[][] b) {
        final String BLUE = "\u001B[34m";
        final String RED = "\u001B[31m";
        final String WHITE = "\u001B[37m";
        final String RESET = "\u001B[0m";
        System.out.print(WHITE);
        System.out.print("  ");
        for (int j = 0; j < 3; j++) {
            System.out.print(BLUE + j + WHITE + " ");
        }
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.print(BLUE + i + WHITE + " ");
            for (int j = 0; j < 3; j++) {
                if (b[i][j] == 'X' || b[i][j] == 'O')
                    System.out.print(RED + b[i][j] + WHITE);
                else
                    System.out.print(b[i][j]);
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("  -----");
        }
        System.out.print(RESET);
    }
    private static boolean win(char[][] b, char p) {
        for (int i = 0; i < 3; i++) if (b[i][0] == p && b[i][1] == p && b[i][2] == p) return true;
        for (int i = 0; i < 3; i++) if (b[0][i] == p && b[1][i] == p && b[2][i] == p) return true;
        if (b[0][0] == p && b[1][1] == p && b[2][2] == p) return true;
        if (b[0][2] == p && b[1][1] == p && b[2][0] == p) return true;
        return false;
    }
    private static boolean full(char[][] b) {
        for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) if (b[i][j] == ' ') return false;
        return true;
    }
}