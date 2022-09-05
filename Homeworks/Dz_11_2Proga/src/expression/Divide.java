package expression;

import java.util.Objects;

public class Divide extends Operation {
    public Divide(MultiExpression exp1, MultiExpression exp2) {
        super(exp1, exp2, "/");
    }

    @Override
    protected int op(int first, int second) {
        return first / second;
    }

}
