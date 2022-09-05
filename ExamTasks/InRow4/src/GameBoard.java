/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class GameBoard implements Position {
    private final int m;
    private final int n;
    private Cell[][] cells;
    private Cell turn;
    private int[] available;
    private int filledCells = 0;
    private int Row;
    private int Col;

    public GameBoard() { // Создание доски
        // о m n узнаем из Game.
        this.m = Game.m;
        this.n = Game.n;
        available = new int[n];
        cells = new Cell[m][n];
        for (int i = 0; i < n; i++) {
            available[i] = Game.m - 1;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = Cell.E;
            }
        }
        turn = Cell.W;
    }

    public Position getPosition() {
        return this;
    }

    public Cell getCell() {
        return turn;
    }


    public Result makeMove(final Move move) { // Проверка выйгрыша, заполнение таблицы новым ходом
        int x = move.getX();
        turn = move.getValue();
        cells[available[x]][x] = turn;
        filledCells++;
        int inDiag1 = -1;
        int inDiag2 = -1;
        int inRow = -1;
        int inColumn = -1;
        Row = available[x];;
        Col = x;
        available[x]--;
        int rowTurn = Row;
        int colTurn = Col;
        while (checkCell(rowTurn, colTurn)) { // check right
            rowTurn++;
            inRow++;
        }
        rowTurn = Row;
        colTurn = Col;
        while (checkCell(rowTurn, colTurn)) { // check left
            rowTurn--;
            inRow++;
        }
        rowTurn = Row;
        colTurn = Col;
        while (checkCell(rowTurn, colTurn)) { // Check up
            colTurn++;
            inColumn++;
        }
        rowTurn = Row;
        colTurn = Col;
        while (checkCell(rowTurn, colTurn)) { // check bottom
            colTurn--;
            inColumn++;
        }
        rowTurn = Row;
        colTurn = Col;
        while (checkCell(rowTurn, colTurn)) { // check up-right
            rowTurn++;
            colTurn++;
            inDiag1++;
        }
        rowTurn = Row;
        colTurn = Col;
        while (checkCell(rowTurn, colTurn)) { // check bottom-left
            rowTurn--;
            colTurn--;
            inDiag1++;
        }
        rowTurn = Row;
        colTurn = Col;
        while (checkCell(rowTurn, colTurn)) { // check up-left
            rowTurn--;
            colTurn++;
            inDiag2++;
        }
        rowTurn = Row;
        colTurn = Col;
        while (checkCell(rowTurn, colTurn)) { // check bottom-right
            rowTurn++;
            colTurn--;
            inDiag2++;
        }
        if (inRow >= 4 || inColumn >= 4 || inDiag1 >= 4 || inDiag2 >= 4) {
            System.out.println(getPosition());
            if (turn == Cell.W) return Result.FIRSTWIN;
            return Result.SECONDWIN;
        }
        if (filledCells == Game.m*Game.n) {
            System.out.println(getPosition());
            return Result.DRAW;
        }
        turn = turn == Cell.W ? Cell.B : Cell.W;
        return Result.UNKNOWN;
    }
    private boolean checkCell(int rowTurn, int colTurn){
        return (rowTurn < Game.m && colTurn < Game.n && rowTurn >=0 && colTurn >= 0 && cells[rowTurn][colTurn] == turn);
    }

    public boolean isValid(final Move move) {
        return 0 <= move.getX() && move.getX() < n && available[move.getX()] >= 0;
    }

    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("   ");
        for (int i = 1; i < n + 1; i++) {
            sb.append(i).append(" ");
        }
        sb.append("\n");
        for (int i = 0; i < m; i++) {
            sb.append(i + 1).append("| ");
            for (int j = 0; j < n; j++) {
                sb.append(cells[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
