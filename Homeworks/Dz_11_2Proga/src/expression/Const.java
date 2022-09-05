package expression;

import java.util.Objects;

public class Const implements MultiExpression {
    int x;
    public Const(int x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return Integer.toString(x);
    }

    @Override
    public int evaluate(int x) {
        return this.x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return this.x;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(x);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Const aConst = (Const) o;
        return x == aConst.x;
    }
}
