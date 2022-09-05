
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final int cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println("player" + cell + " move");
            out.println("Enter row and column");
            System.out.println();
            String line = in.next();
            while (check_correct_coordinates(line)){
                line = in.next();
            }
            int x = Integer.parseInt(line);
            line = in.next();
            while (check_correct_coordinates(line)){
                line = in.next();
            }
            int y = Integer.parseInt(line);
            final Move move = new Move(x, y, cell);
            if (position.isValid(move)) {
                return move;
            }
            out.println("Move " + move + " is engaged");
        }
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
