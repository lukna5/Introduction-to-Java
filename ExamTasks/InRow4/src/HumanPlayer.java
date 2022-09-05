import java.io.PrintStream;
import java.util.Scanner;

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
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            String line = in.next();// обработка одной переменно хода
            while (check_correct_coordinates(line)){
                line = in.next();
            }
            int x = Integer.parseInt(line) - 1;//
            final Move move = new Move(x, cell); // создаем ход а потом проверяем его корректность на незанятость и гранички
            if (position.isValid(move)) { // -1 чтобы преобразовать нормальные координаты к массивным
                return move;
            }
            out.println("Move " + move + " is bad");
        }
    }
    public static boolean check_correct_coordinates(String number){
        boolean Badinput = false;
        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) Badinput = true;
        }
        if (!Badinput && (Integer.parseInt(number) < 1 || Integer.parseInt(number) > 8))
            Badinput = true;
        if(Badinput) System.out.println("Please enter positive digit < 7 and > 0");
        return Badinput;
    }
}
