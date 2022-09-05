
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class TicTacToeBoard implements Board, Position {


    private final String[][] palki;
    private final Cell[][] cells;
    private int turn;
    private ArrayList<Integer> scoreList = new ArrayList<>();
    private ArrayList<Integer> winners = new ArrayList<>();
    private int filled_cells = 0;

    public TicTacToeBoard() {
        this.palki = new String[2 * Game.m][2 * Game.n];
        for (int i = 0; i < 2 * Game.m; i++) {
            for (int j = 0; j < 2 * Game.n; j++) {
                if (i % 2 == 0 && j % 2 == 0 || i % 2 == 1 && j % 2 == 1) palki[i][j] = " ";
                else palki[i][j] = "*";
            }
        }
        this.cells = new Cell[Game.m][Game.n];
        for (int i = 1; i < Main.numP + 1; i++) {
            scoreList.add(0);
        }
        turn = 1;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public int getCell() {
        return turn;
    }

    @Override
    public int makeMove(final Move move) {
        boolean nextTurn = false;
        int row = move.getRow();
        System.out.println(move.getValue() + " Turn");
        int column = move.getColumn();
        if (column % 2 == 0) {
            palki[row][column] = "—";
            System.out.println(row);
            System.out.println(column);
            if ((column == 0 || palki[row - 1][column - 1].equals("|")) && (column == 2 * Game.n - 2 || palki[row - 1][column + 1].equals("|")) && (row == 1 || palki[row - 2][column].equals("—"))) {
                palki[row - 1][column] = Integer.toString(move.getValue());
                nextTurn = true;
                addScore(move.getValue());
            }
            if ((column == 0 || palki[row + 1][column - 1].equals("|")) && (column == 2 * Game.n - 2 || palki[row + 1][column + 1].equals("|")) && (row == 2 * Game.m - 3 || palki[row + 2][column].equals("—"))) {
                palki[row + 1][column] = Integer.toString(move.getValue());
                nextTurn = true;
                addScore(move.getValue());
            }
        } else {
            palki[row][column] = "|";
            if ((row == 0 || palki[row - 1][column - 1].equals("—")) && (row == 2 * Game.m - 2 || palki[row + 1][column - 1].equals("—")) && (column == 1 || palki[row][column - 2].equals("|"))) {
                palki[row][column - 1] = Integer.toString(move.getValue());
                nextTurn = true;
                addScore(move.getValue());
            }
            if ((row == 0 || palki[row - 1][column + 1].equals("—")) && (row == 2 * Game.m - 2 || palki[row + 1][column + 1].equals("—")) && (column == 2 * Game.n - 3 || palki[row][column + 2].equals("|"))) {
                palki[row][column + 1] = Integer.toString(move.getValue());
                nextTurn = true;
                addScore(move.getValue());
            }
        }
        filled_cells++;
        System.out.println(filled_cells);
        if (filled_cells == (Game.m - 1) * Game.n + (Game.n - 1) * Game.m){
            System.out.println(getPosition());
            //System.out.println(move.getValue());
            int max = 0;
            int maxi = 0;
            for (int i = 0; i < scoreList.size(); i++) {
                if (max < scoreList.get(i)){
                    max = scoreList.get(i);
                    maxi = i;
                }
            }
            for (int i = 0; i < scoreList.size(); i++) {
                if (max == scoreList.get(i)) winners.add(i);
            }
            if (winners.size() > 1) {
                System.out.println("Draw, Players with most score :");
                for (int i = 0; i < winners.size(); i++) {
                    System.out.print(winners.get(i) + " ");
                }
                return 0;
            } else return maxi + 1;
        }
        if (!nextTurn) {
            changeTurn();
            return -1;
        }
        return -2;
    }
    private void changeTurn(){
        turn = (turn % Main.numP) + 1;
    }
    private void addScore(int cell){
        scoreList.set(cell - 1, scoreList.get(cell - 1) + 1);
    }
    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < 2 * Game.m - 1
                && 0 <= move.getColumn() && move.getColumn() < 2 * Game.n - 1
                && palki[move.getRow()][move.getColumn()].equals("*") && ((move.getRow() % 2) + (move.getColumn() % 2) == 1);
    }

    @Override
    public String getCell(final int r, final int c) {
        return palki[r][c];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("    ");
        for (int i = 0; i < 2 * Game.n - 1; i++) {
            sb.append(i).append(" ");
        }
        sb.append("\n").append("    ");
        for (int i = 0; i < 2 * Game.n; i += 2) {
            sb.append("—").append("   ");
        }
        for (int r = 0; r < 2 * Game.m - 1; r++) {
            sb.append("\n");
            sb.append(r);
            if (r % 2 == 0) {
                sb.append(" | ");
                for (int c = 0; c < 2 * Game.n - 1; c++) {
                    sb.append(palki[r][c] + " ");
                }
                //sb.setLength(sb.length() - 1);
                sb.append(" |");
            }
            else {
                sb.append("   ");
                for (int c = 0; c < 2 * Game.n  - 1; c+=2) {
                    sb.append(palki[r][c] + "   ");
                }

            }
        }
        sb.append("\n    ");
        for (int i = 0; i < Game.n; i++) {
            sb.append("—   ");
        }
        return sb.toString();
    }
}
