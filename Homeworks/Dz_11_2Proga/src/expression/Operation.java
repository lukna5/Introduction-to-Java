package expression;

import java.util.Objects;

public abstract class Operation implements MultiExpression {
    protected final MultiExpression exp1;
    protected final MultiExpression exp2;
    private final String operate;

    public Operation(final MultiExpression exp1, final MultiExpression exp2, String operate) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operate = operate;
    }
    public String toString(){
        return "(" + exp1.toString() + " " + operate + " " + exp2.toString() + ")";
    }
    @Override
    public int evaluate(int x) {
        return op(exp1.evaluate(x), exp2.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return op(exp1.evaluate(x, y ,z), exp2.evaluate(x, y, z));
    }

    public boolean equals(Object o) {
        if (o != null) {
            if (getClass() == o.getClass()) {
                Operation operation = (Operation) o;
                return exp1.equals(operation.exp1) && exp2.equals(operation.exp2);
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return exp1.hashCode()*3 + exp2.hashCode() *6 - getClass().hashCode();
    }
    protected abstract int op(int first, int second);
}
