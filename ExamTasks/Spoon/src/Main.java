import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        //while (reader.read() != 0) builder.append(Character.getNumericValue(reader.read()));
        //new Spoon(Integer.parseInt(builder.toString()), reader);
        new Spoon(args[0], args[1], reader);
    }
}
