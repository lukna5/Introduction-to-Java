package Reversi;
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
    public Move move(final Position position, final Cell cell, int numberP) {
        boolean haveTurn = false;
        for (int i = 0; i < 8; i++) { // проверяем есть и вообще возможность игроку сходить
            for (int j = 0; j < 8; j++) {
                final Move move = new Move(i, j, cell, numberP); // создаем ход а потом проверяем его корректность на незанятость и гранички
                //System.out.println(i + " " + j);
                if (position.isValid(move)) { // -1 чтобы преобразовать нормальные координаты к массивным
                    haveTurn = true;
                    break;
                }
            }
        }
        Cell cell1;
        if (cell == Cell.B) cell1 = Cell.W;
        else cell1 = Cell.B;
        if (haveTurn) {
            while (true) {
                out.println("Position");
                out.println(position);
                out.println(cell + "'s move");
                System.out.println("Enter coordinates (letter and number) only 2 symbols");
                String line = in.nextLine().toLowerCase();// обработка одной переменно хода
                while (line.length() != 2 || !(line.charAt(0) < 105 && line.charAt(0) > 96 && line.charAt(1) > 48 && line.charAt(1) < 58)) {
                    System.out.println("Enter correct coordinates| letter and number");
                    line = in.next().toLowerCase();
                }
                int y = line.charAt(0) - 97;//
                int x = 7 - (line.charAt(1) - 49);//
                System.out.println(x + " " + y);
                final Move move = new Move(x, y, cell, numberP); // создаем ход а потом проверяем его корректность на незанятость и гранички
                if (position.isBasicValid(move)) { // -1 чтобы преобразовать нормальные координаты к массивным
                    return move;
                }
                out.println("Move " + move + " is bad");
            }
        } else { // проверяем есть ли возможность сходить второму игроку
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    final Move move = new Move(i, j, cell1 , numberP); // создаем ход а потом проверяем его корректность на незанятость и гранички
                    if (position.isValid(move)) { // -1 чтобы преобразовать нормальные координаты к массивным
                        haveTurn = true;
                        break;
                    }
                }
            }
        }
        if (haveTurn) return new Move(-1, 0, cell , numberP); // второй игрок может сходить значит даем ему ход
        return new Move(0, -1, cell , numberP); // никто не может сходить. Значит заканчиваем
    }
}
