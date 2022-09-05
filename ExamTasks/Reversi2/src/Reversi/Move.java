package Reversi;
public final class Move {
    private final int x;
    private final int y;
    private final Cell value;
    private final int numberP;
    public Move(final int x, final int y, Cell value, int numberP) {
        this.numberP = numberP;
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getNumberP() {
        return numberP;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Cell getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "x =" + x + ", y = " + y;
    }
}