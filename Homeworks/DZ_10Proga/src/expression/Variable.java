package expression;

import java.util.Objects;

public class Variable implements Expression, TripleExpression {
    String var;
    public Variable(String var) {
        this.var = var;
    }
    public String toString() {
        return (var);
    }
    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if(var.equals("x")){
            return x;
        }
        if (var.equals("y")) {
            return y;
        }
        if (var.equals("z")){
            return z;
        }
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return Objects.equals(var, variable.var);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(var);
    }
}
