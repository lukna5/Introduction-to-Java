import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            list.add(args[i]);
        }
        System.out.println("f" + Character.toString(0) + "f");
        Scanner input = new Scanner(System.in);
        new Sort(list, input);
    }
}
