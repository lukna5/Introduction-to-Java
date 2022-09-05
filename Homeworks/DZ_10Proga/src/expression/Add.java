package expression;

public class Add extends Operation {
    public Add(Expression exp1, Expression exp2) {
        super(exp1, exp2, "+");
    }

    @Override
    public String toString() {
        return "(" + exp1.toString() + " + " + exp2.toString() + ")";
    }

    @Override
    public int evaluate(int x) {
        return exp1.evaluate(x) + exp2.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        TripleExpression first = (TripleExpression) exp1;
        TripleExpression second = (TripleExpression) exp2;
        return first.evaluate(x, y, z) + second.evaluate(x, y, z);
    }
}
