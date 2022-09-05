package expression.parser;

import expression.*;

public class ExpressionParser implements Parser {
    private  int pos = 0;
    private String text;

    protected void back() {
        pos--;
    }

    public MultiExpression parse(String expression) {
        text = expression;
        String c = takeNext();
        if (c.equals("\n")) {
            return null;
        } else {
            back();
            return priorityOr();
        }
    }
    protected MultiExpression priorityOr(){
        MultiExpression value = priorityXor();
        while (true) {
            String lexeme = takeNext();
            switch (lexeme) {
                case "&", "^", "|" -> value = new BitwiseOr(value, priorityXor());
                case "\n", ")" -> {
                    back();
                    return value;
                }
                default -> throw new RuntimeException("Unexpected token: ");
            }
        }
    }
    protected MultiExpression priorityXor() {
        MultiExpression value = priorityAnd();
        while (true) {
            String lexeme = takeNext();
            switch (lexeme) {
                case "&", "^" -> value = new BitwiseXor(value, priorityAnd());
                case "|", "\n", ")" -> {
                    back();
                    return value;
                }
                default -> throw new RuntimeException("Unexpected token: ");
            }
        }
    }
    protected MultiExpression priorityAnd() {
        MultiExpression value = priority1();
        while (true) {
            String lexeme = takeNext();
            switch (lexeme) {
                case "&" -> value = new BitwiseAnd(value, priority1());
                case "^", "|", "\n", ")" -> {
                    back();
                    return value;
                }
                default -> throw new RuntimeException("Unexpected token: ");
            }
        }
    }
    protected MultiExpression priority1() {
        MultiExpression value = priority2();
        while (true) {
            String lexeme = takeNext();
            switch (lexeme) {
                case "+" -> value = new Add(value, priority2());
                case "-" -> value = new Subtract(value, priority2());
                case "\n", ")", "&", "|", "^" -> {
                    back();
                    return value;
                }
            }
        }
    }
    protected MultiExpression priority2(){
        MultiExpression value = priority3();
        while (true) {
            String lexeme = takeNext();
            switch (lexeme) {
                case "*" -> value = new Multiply(value, priority3());
                case "/" -> value = new Divide(value, priority3());
                case "\n", ")", "+", "-", "&", "|", "^" -> {
                    back();
                    return value;
                }
            }
        }
    }
    protected MultiExpression priority3() {
        String c = takeNext();
        String enter = c;
        StringBuilder number = new StringBuilder();
        if (isDigit(enter)){
            while (isDigit(c)){
                number.append(c);
                c = takeNext();
            }
            back();
            return new Const(Integer.parseInt(number.toString()));
        }
        else if (enter.equals("x")){
            return new Variable("x");
        }
        else if (enter.equals("y")){
            return new Variable("y");
        }
        else if (enter.equals("z")){
            return new Variable("z");
        }
        else if (enter.equals("(")){
            MultiExpression value = priorityOr();
            pos++;
            return value;
        }else if (enter.equals("-")){
            c = takeNext();
            enter = c;
            if (isDigit(enter)){
                while (isDigit(c)){
                    number.append(c);
                    c = takeNext();
                }
                back();
                return new Const(Integer.parseInt("-" + number.toString()));
            }
            else if (enter.equals("x")){
                return new Multiply(new Variable("x"), new Const(-1));
            }
            else if (enter.equals("y")){
                return new Multiply(new Variable("y"), new Const(-1));
            }
            else if (enter.equals("z")){
                return new Multiply(new Variable("z"), new Const(-1));
            }
            back();
            MultiExpression value1 =  priority3();
            return new UnarMinus(value1);
        }
        return null;
    }
    protected String takeNext(){
        if (pos < text.length()) {
            while (pos != text.length() && !(isDigit(Character.toString(text.charAt(pos))) || isOperation(Character.toString(text.charAt(pos))))) {
                pos++;
            }
            if (pos >= text.length()) return "\n";
            return Character.toString(text.charAt(pos++));
        }
        else{
            pos++;
            return "\n";
        }
    }
    protected boolean isDigit(String suspect){
        if (suspect.length() == 0) return false;
        for (int i = 0; i < suspect.length(); i++) {
            if (!Character.isDigit(suspect.charAt(i))) return false;
        }
        return true;
    }
    protected boolean isOperation(String suspect){
        return switch (suspect) {
            case "+", "-", "*", "/", "(", ")", "x", "y", "z", "^", "|", "&" -> true;
            default -> false;
        };
    }
}


