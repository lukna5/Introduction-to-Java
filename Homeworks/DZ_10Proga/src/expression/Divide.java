package expression;

import java.util.Objects;

public class Divide extends Operation {
    public Divide(Expression exp1, Expression exp2) {
        super(exp1, exp2, "/");
    }

    @Override
    public int evaluate(int x) {
        return exp1.evaluate(x) / exp2.evaluate(x);
    }
    @Override
    public int evaluate(int x, int y, int z) {
        TripleExpression first = (TripleExpression) exp1;
        TripleExpression second = (TripleExpression) exp2;
        return first.evaluate(x, y, z) / second.evaluate(x, y, z);
    }

}
