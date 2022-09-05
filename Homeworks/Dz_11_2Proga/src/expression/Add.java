package expression;

public class Add extends Operation {
    public Add(MultiExpression exp1, MultiExpression exp2) {
        super(exp1, exp2, "+");
    }

    @Override
    protected int op(int first, int second) {
        return first + second;
    }

}
