package Reversi;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        System.out.println("Please enter. How many tournament wins?");
        Scanner input = new Scanner(System.in);
        String line = input.next();
        while (checkCorrect(line)) {
            line = input.next();;
        }
        int toWin = Integer.parseInt(line);
        final Game game = new Game(new HumanPlayer(), new HumanPlayer());
        int result;
        int winsPlayer1 = 0;
        int winsPlayer2 = 0;
        int numberOfRounds = 0;
        do {
            if (numberOfRounds % 2 == 0) {
                System.out.println("player1 for X, player2 for O");
                result = game.play(new GameBoard(), 1);
            } else {
                System.out.println("player1 for O, player2 for X");
                result = game.play(new GameBoard(), 2);
            }
            if (result == 1) {
                System.out.println("player" + result + " wins round");
                winsPlayer1++;
            } else if (result == 2) {
                System.out.println("player" + result + " wins round");
                winsPlayer2++;
            } else System.out.println("draw");
            System.out.println("Score " + winsPlayer1 + ":" + winsPlayer2);
            numberOfRounds++;

        } while (winsPlayer1 != toWin && winsPlayer2 != toWin);
        if (winsPlayer1 == toWin) System.out.println("Congratulations!!! player1 wins tournament!!!!!");
        else System.out.println("Congratulations!!! player2 wins tournament!!!!!");
    }

    public static boolean checkCorrect(String number){
        boolean badInput = false;
        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) return false;
        }
        if (Integer.parseInt(number) <= 0)
            badInput = true;
        if(badInput) System.out.println("Please enter positive digits");
        return badInput;
    }
}






/* Реализация Турнира
//
System.out.println("Please enter. How many tournament wins?");
            String line = input.next();
            while (Check_correct(line)) {
                line = input.next();
            }
            int to_win = Integer.parseInt(line);
            //
final Game game = new Game(new HumanPlayer(), new HumanPlayer());
            int result;
            int wins_player1 = 0;
            int wins_player2 = 0;
            int number_of_rounds = 0;
            do {
                if (number_of_rounds % 2 == 0) {
                    System.out.println("player1 for X, player2 for O");
                    result = game.play(new TicTacToeBoard(), 1);
                } else {
                    System.out.println("player1 for O, player2 for X");
                    result = game.play(new TicTacToeBoard(), 2);
                }
                if (result == 1) {
                    System.out.println("player" + result + " wins round");
                    wins_player1++;
                } else if (result == 2) {
                    System.out.println("player" + result + " wins round");
                    wins_player2++;
                } else System.out.println("draw");
                System.out.println("Score " + wins_player1 + ":" + wins_player2);
                number_of_rounds++;

            } while (wins_player1 != to_win && wins_player2 != to_win);
            if (wins_player1 == to_win) System.out.println("Congratulations!!! player1 wins tournament!!!!!");
            else System.out.println("Congratulations!!! player2 wins tournament!!!!!");
 */
