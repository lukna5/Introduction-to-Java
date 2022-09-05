import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class RandomPlayer implements Player {
    private final PrintStream out;

    public RandomPlayer(final PrintStream out) {
        this.out = out;
    }

    public RandomPlayer() {
        this(System.out);
    }

    @Override
    public Move move(final Position position, final int cell) {
            out.println(position);
            System.out.println();
            Random random = new Random();
            int x = random.nextInt(2 * Game.m - 1);
            int y = random.nextInt(2 * Game.n - 1);
            Move move = new Move(x, y, cell);
            while (!position.isValid(move)){
                x = random.nextInt(2 * Game.m - 1);
                y = random.nextInt(2 * Game.n - 1);
                move = new Move(x, y, cell);
            }
            /*if (!position.isValid(move)) {
                for (int i = x; i < 2 * Game.m - 2; i++) {
                    for (int j = y; j < 2 * Game.n - 2; j++) {
                        move = new Move(i, j, cell);
                        //System.out.println(i + " " + j);
                        //System.out.println(position.isValid(move));
                        if (position.isValid(move)){
                            return move;
                        }
                    }
                    if (i == 2 * Game.m - 1) i = 0;
                }
            }

             */
            return move;
    }
    public static boolean check_correct_coordinates(String number){
        boolean unnormal_input = false;
        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) unnormal_input = true;
        }
        if (!unnormal_input && Integer.parseInt(number) < 0)
            unnormal_input = true;
        if(unnormal_input) System.out.println("Please enter not negative digit");
        return unnormal_input;
    }
}