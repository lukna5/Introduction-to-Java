package mnk;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class TicTacToeBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private Cell turn;
    private int filled_cells = 0;
    private int Row_turn;
    private int Col_turn;
    public int Row;
    public int Col;

    public TicTacToeBoard() {
        this.cells = new Cell[Game.m][Game.n];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        cells[move.getRow()][move.getColumn()] = move.getValue();
        filled_cells++;
        int inDiag1 = -1;
        int inDiag2 = -1;
        int inRow = -1;
        int inColumn = -1;
        Row = move.getRow();
        Col = move.getColumn();
        Update();
        while (Check_cell()) { // check right
            Row_turn++;
            inRow++;
        }
        Update();
        while (Check_cell()) { // check left
            Row_turn--;
            inRow++;
        }
        Update();
        while (Check_cell()) { // Check up
            Col_turn++;
            inColumn++;
        }
        Update();
        while (Check_cell()) { // check bottom
            Col_turn--;
            inColumn++;
        }
        Update();
        while (Check_cell()) { // check up-right
            Row_turn++;
            Col_turn++;
            inDiag1++;
        }
        Update();
        while (Check_cell()) { // check bottom-left
            Row_turn--;
            Col_turn--;
            inDiag1++;
        }
        Update();
        while (Check_cell()) { // check up-left
            Row_turn--;
            Col_turn++;
            inDiag2++;
        }
        Update();
        while (Check_cell()) { // check bottom-right
            Row_turn++;
            Col_turn--;
            inDiag2++;
        }
        if (inRow >= Game.k || inColumn >= Game.k || inDiag1 >= Game.k || inDiag2 >= Game.k) {
            return Result.WIN;
        }
        if (filled_cells == Game.m*Game.n) {
            return Result.DRAW;
        }
        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < Game.m
                && 0 <= move.getColumn() && move.getColumn() < Game.n
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(" ");
        for (int i = 0; i < Game.n; i++) {
            sb.append(i);
        }
        for (int r = 0; r < Game.m; r++) {
            sb.append("\n");
            sb.append(r);
            for (int c = 0; c < Game.n; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }
    public void Update(){
        Row_turn = Row;
        Col_turn = Col;
    }
    private boolean Check_cell(){
        return (Row_turn < Game.m && Col_turn < Game.n && Row_turn >=0 && Col_turn >= 0 && cells[Row_turn][Col_turn] == turn);
    }

}
