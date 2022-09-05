package expression.parser;

import expression.*;

public class Main {
    public static void main(String[] args) {
        String line = "10+x *4 - (3+2)";
        //System.out.println(Character.toString(line.charAt(0)));
        final ExpressionParser parser = new ExpressionParser();
        final TripleExpression expression = parser.parse(line);
        System.out.println(expression);
        //System.out.println(expression.parse(line).evaluate(10,0,0));
    }
}
