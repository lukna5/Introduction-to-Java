
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static int numP;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter number of players");
        String line = input.next();
        List<Player> playerList = new ArrayList<>();
        while (Check_correct(line)) {
            line = input.next();
        }
        numP = Integer.parseInt(line);
        for (int i = 0; i < numP; i++) {
            line = input.next();
            while (Check_correct(line)) {
                line = input.next();
            }
            if (Integer.parseInt(line) == 1) playerList.add(new HumanPlayer());
            else if(Integer.parseInt(line) == 2) playerList.add(new RandomPlayer());
        }
        final Game game = new Game(playerList);
        int result;
        result = game.play(new TicTacToeBoard());
        if (result != 0) {
            System.out.println("player" + result + " wins round");
        }
    }

    public static boolean Check_correct(String number){
        boolean unnormal_input = false;
        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) unnormal_input = true;
        }
        if (!unnormal_input && Integer.parseInt(number) <= 0)
            unnormal_input = true;
        if(unnormal_input) System.out.println("Please enter positive digits");
        return unnormal_input;
    }
}
