package expression;

public class UnarMinus implements MultiExpression{
    MultiExpression expression;
    public UnarMinus(MultiExpression expression) {
        this.expression = expression;
    }

    public String toString() {
        return "-";
    }

    @Override
    public int evaluate(int x) {
        return -1 * expression.evaluate(x);
    }
    @Override
    public int evaluate(int x, int y, int z) {
        return -1 * expression.evaluate(x, y, z);
    }
}
