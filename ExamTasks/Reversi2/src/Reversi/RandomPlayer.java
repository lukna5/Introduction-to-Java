package Reversi;
import java.util.Random;


public class RandomPlayer implements Player {
    private final Random random;

    public RandomPlayer(final Random random) {
        this.random = random;
    }

    public RandomPlayer() {
        this(new Random());
    }

    @Override
    public Move move(final Position position, final Cell cell, int numberP) {
        boolean haveTurn = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                final Move move = new Move(i, j, cell, numberP); // создаем ход а потом проверяем его корректность на незанятость и гранички
                if (position.isValid(move)) { // -1 чтобы преобразовать нормальные координаты к массивным
                    haveTurn = true;
                    i = 8;
                    break;
                }
            }
        }
        Cell cell1;
        if (cell == Cell.B) cell1 = Cell.W;
        else cell1 = Cell.B;
        if (haveTurn) {
            while (true) {
                int x = random.nextInt(8);
                int y = random.nextInt(8);
                final Move move = new Move(x, y, cell, numberP);
                if (position.isValid(move)) {
                    return move;
                }
            }
        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    final Move move = new Move(i, j, cell1, numberP); // создаем ход а потом проверяем его корректность на незанятость и гранички
                    if (position.isValid(move)) { // -1 чтобы преобразовать нормальные координаты к массивным
                        haveTurn = true;
                        break;
                    }
                }
            }
        }
        if (haveTurn) return new Move(-1, 0, cell, numberP);
        return new Move(0, -1, cell, numberP);
    }
}

