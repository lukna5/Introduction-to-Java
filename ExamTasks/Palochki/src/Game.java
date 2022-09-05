
import java.util.List;
import java.util.Scanner;

public class Game {
    public static int m;
    public static int n;
    private final List<Player> playerList;
    public Game(final List<Player> playerList) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of lines");
        String checking = input.next();
        while (Main.Check_correct(checking)) {
            checking = input.next();
        }
        m = Integer.parseInt(checking);
        System.out.println("Enter number of columns");
        checking = input.next();
        while (Main.Check_correct(checking)) {
            checking = input.next();
        }
        n = Integer.parseInt(checking);
        this.playerList = playerList;
    }
    public int play(Board board) {
        while (true) {
            for (int i = 0; i < playerList.size(); i++) {
                final int result1 = move(board, playerList.get(i));
                if (result1 == -2){
                    if (i == 0) i = playerList.size() - 2;
                    else i--;
                }
                else if (result1 != -1) {
                    return result1;
                }
            }
        }
    }

    private int move(final Board board, final Player player) {
        //System.out.println(board.getPosition() + " " + board.getCell());
        final Move move = player.move(board.getPosition(), board.getCell());
        return board.makeMove(move);
    }
}
