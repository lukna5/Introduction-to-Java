package expression;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Subtract(new Const(5), new Const(2)).evaluate(0));

    }
}
