package Reversi;
public interface Position {
    boolean isValid(Move move);
    boolean isBasicValid(Move move);
    Cell getCell(int r, int c);
}
