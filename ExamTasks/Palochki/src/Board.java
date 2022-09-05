
/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Board {
    Position getPosition();
    int getCell();
    int makeMove(Move move);
}
