
public final class Move {
    private final int x;
    private final Cell value;

    public Move(final int x, Cell value) {
        this.x = x;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public Cell getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "x =" + x;
    }
}