package expression;

public class BitwiseOr extends Operation {
    public BitwiseOr(MultiExpression exp1, MultiExpression exp2) {
        super(exp1, exp2, "|");
    }

    @Override
    protected int op(int first, int second) {
        return first | second;
    }
}
