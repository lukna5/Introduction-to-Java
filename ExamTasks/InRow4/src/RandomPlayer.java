
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
    public Move move(final Position position, final Cell cell) {
        while (true) {
            int r = random.nextInt(6);
            final Move move = new Move(r, cell);
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
