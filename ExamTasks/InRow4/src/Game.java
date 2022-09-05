

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static int m;
    public static int n;
    private final ArrayList<Player> players = new ArrayList<>();
    public Game(final Player player1, final Player player2) {
        Scanner input = new Scanner(System.in); // Обьявление переменных для игры
        //мультиплеер, добавляем всех игрок и через форик даем каждому играть если
        // повтор хода то просто откатываем i
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
        players.add(player1);
        players.add(player2);
    }
    public int play(GameBoard board) { // Смена ходов
        while (true){
            for (int i = 0; i < players.size(); i++) {
                final int result = move(board, players.get(i));
                if (result >= 0) {
                    return result;
                } else if (result == -2) {
                    if (i == 0) i = players.size() - 2;
                    else i--;
                }
            }
        }
    }

    private int move(final GameBoard board, final Player player) {
        final Move move = player.move(board.getPosition(), board.getCell());
        final Result result = board.makeMove(move);
        if (result == Result.NEWTURN) return -2;
        if (result == Result.FIRSTWIN) return 1;
        if (result == Result.SECONDWIN) return 2;
        if (result == Result.DRAW) return 0;
        return -1; // Next player
    }
}




/* Обработка входных условий доски
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
 */



/* Если турнир
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
 */

/* тривиальный play
        while (true) {
final int result1 = move(board, player1, 1);
        if (result1 >= 0) {
        return result1;
        } else if (result1 == -2) continue;
final int result2 = move(board, player2, 2);
        if (result2 >= 0) {
        return result2;
        } else if (result2 == -2)
        }

 */

