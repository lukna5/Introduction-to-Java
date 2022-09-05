package mnk;

import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Game {
    public static int m;
    public static int n;
    private final boolean log;
    private final Player player1;
    private final Player player2;
    public static int k;

    public Game(final boolean log, final Player player1, final Player player2) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of lines");
        String checking = input.next();
        while (Main.Check_correct(checking)) {
            checking = input.next();
        }
        m = Integer.parseInt(checking);
        System.out.println("Enter number of columns");
        checking = input.next();
        while (Main.Check_correct(checking)) {
            checking = input.next();
        }
        n = Integer.parseInt(checking);
        System.out.println("Enter length of line to win");
        checking = input.next();
        while (Main.Check_correct(checking)) {
            checking = input.next();
        }
        k = Integer.parseInt(checking);
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
    }
    public int play(Board board, int first) {
        if (first == 1)
        while (true) {
            final int result1 = move(board, player1, 1);
            if (result1 != -1) {
                return result1;
            }
            final int result2 = move(board, player2, 2);
            if (result2 != -1) {
                return result2;
            }
        }
        else {
            while (true) {
                final int result2 = move(board, player2, 2);
                if (result2 != -1) {
                    return result2;
                }
                final int result1 = move(board, player1, 1);
                if (result1 != -1) {
                    return result1;
                }
            }
        }
    }

    private int move(final Board board, final Player player, final int no) {
        System.out.println(board.getPosition() + " " + board.getCell());
        final Move move = player.move(board.getPosition(), board.getCell());
        final Result result = board.makeMove(move);
        log("Player " + no + " move: " + move);
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + no + " won");
            return no;
        } else if (result == Result.LOSE) {
            log("Player " + no + " lose");
            return 3 - no;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else {
            return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
