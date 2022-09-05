package Reversi;

public class GameBoard implements Position {
    private final int m;
    private final int n;
    private int countB = 0;
    private int countW = 0;
    private int count = 4;
    private final Cell[][] cells;
    private Cell turn;
    private int x1;
    private int y1;
    private int next;
    private int numberP;
    public GameBoard() { // Создание доски
        // о m n узнаем из Game.
        this.m = 8;
        this.n = 8;
        cells = new Cell[m][n];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i][j] = Cell.E;
            }
        }
        cells[3][4] = Cell.B;
        cells[4][3] = Cell.B;
        cells[3][3] = Cell.W;
        cells[4][4] = Cell.W;
        turn = Cell.B;
    }

    public Position getPosition() {
        return this;
    }

    public Cell getCell() {
        return turn;
    }


    public Result makeMove(final Move move) { // Проверка выйгрыша, заполнение таблицы новым ходом
        boolean added = false;
        int x = move.getX();
        int y = move.getY();
        numberP = move.getNumberP();
        if (x == -1){
            if (turn == Cell.B) turn = Cell.W;
            else turn = Cell.B;
            return Result.UNKNOWN;
        }
        if (y == -1) {
            if (countB > countW) return Result.FIRSTWIN;
            if (countW > countB) return Result.SECONDWIN;
            return Result.DRAW;
        }
        x1 = x;
        y1 = y;
        turn = move.getValue();
        cells[x][y] = turn;
        next = 3;
        count++;
        // Проверка выйгрыша
        while (next == 3){
            x1++;
            next = checkCell(x1, y1, turn);
            if (next == 1){
                while (x1 != x){
                    add(turn);
                    added = Math.abs(x1 - x) > 1 || added;
                    x1--;
                    cells[x1][y] = turn;
                }
            }
        }
        update(x, y);
        while (next == 3){
            x1--;
            next = checkCell(x1, y1, turn);
            if (next == 1){
                while (x1 != x){
                    add(turn);
                    added = Math.abs(x1 - x) > 1 || added;
                    x1++;
                    cells[x1][y] = turn;
                }
            }
        }
        update(x, y);
        while (next == 3){
            y1++;
            next = checkCell(x1, y1, turn);
            if (next == 1){
                while (y1 != y){
                    add(turn);
                    added = Math.abs(y1 - y) > 1 || added;
                    y1--;
                    cells[x][y1] = turn;
                }
            }
        }
        update(x, y);
        while (next == 3){
            y1--;
            next = checkCell(x1, y1, turn);
            if (next == 1){
                while (y1 != y){
                    add(turn);
                    added = Math.abs(y1 - y) > 1 || added;
                    y1++;
                    cells[x][y1] = turn;
                }
            }
        }
        update(x, y);
        while (next == 3){
            x1++;
            y1++;
            next = checkCell(x1, y1, turn);
            if (next == 1){
                while (y1 != y){
                    add(turn);
                    added = Math.abs(y1 - y) > 1 || added;
                    y1--;
                    x1--;
                    cells[x1][y1] = turn;
                }
            }
        }
        update(x, y);
        while (next == 3){
            x1++;
            y1--;
            next = checkCell(x1, y1, turn);
            if (next == 1){
                while (x1 != x){
                    add(turn);
                    added = Math.abs(y1 - y) > 1 || added;
                    x1--;
                    y1++;
                    cells[x1][y1] = turn;
                }
            }
        }
        update(x, y);
        while (next == 3){
            x1--;
            y1--;
            next = checkCell(x1, y1, turn);
            if (next == 1){
                while (x1 != x){
                    add(turn);
                    added = Math.abs(y1 - y) > 1 || added;
                    x1++;
                    y1++;
                    cells[x1][y1] = turn;
                }
            }
        }
        update(x, y);
        while (next == 3){
            x1--;
            y1++;
            next = checkCell(x1, y1, turn);
            if (next == 1){
                while (x1 != x){
                    add(turn);
                    added = Math.abs(y1 - y) > 1 || added;
                    x1++;
                    y1--;
                    cells[x1][y1] = turn;
                }
            }
        }
        if (!added){
            count--;
            cells[x][y] = Cell.E;
            System.out.println("You don`t close any opponents cells" + count + " " + turn);
            System.out.println(getPosition());
            return Result.NEWTURN;
        }
        if (turn == Cell.B) turn = Cell.W;
        else turn = Cell.B;
        update(x, y);
        if (count == 64){
            if (countB > countW) {
                if (numberP == 1) return Result.FIRSTWIN;
                return Result.SECONDWIN;
            }
            if (countW > countB){
                if (numberP == 1) return Result.SECONDWIN;
                return Result.FIRSTWIN;
            }
            return Result.DRAW;
        }
        return Result.UNKNOWN;
    }
    private Result getWinner(boolean win){
        if (numberP == 1) return Result.FIRSTWIN;
        return Result.SECONDWIN;
    }
    private int checkCell(int x, int y, Cell cell){
        if (0 <= x && x < 8 && 0 <= y && y < 8){
            if (cell == cells[x][y]) return 1;
            if (Cell.E == cells[x][y]) return 2;
            return 3;
        }
        return 0;// выход за границу
    }
    private void update(int x, int y){
        x1 = x;
        y1 = y;
        next = 3;
    }
    private void add(Cell cell){
        if (cell == Cell.B) countB++;
        else countW++;
    }

    public boolean isValid(final Move move) { // Продумать еще раз проверку корректности позиции поля
        if (0 <= move.getX() && move.getX() < m && 0 <= move.getY() && move.getY() < n && cells[move.getX()][move.getY()] == Cell.E){
            int x = move.getX();
            int y = move.getY();
            Cell turn = move.getValue();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    //System.out.println(i + " " + j + " base");
                    if ((Math.abs(x - i) > 1 || Math.abs(y - j) > 1) && (x == i || y == j || Math.abs(x - i) == Math.abs(y - j)) && cells[i][j] == turn){
                        //System.out.println(i + " ii " + j);
                        if (correct(i - x, j - y, x, y, turn)) {
                            //System.out.println(i + " " + j + " coordin");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public boolean isBasicValid(final Move move){
        return 0 <= move.getX() && move.getX() < m && 0 <= move.getY() && move.getY() < n && cells[move.getX()][move.getY()] == Cell.E;
    }
    private boolean correct(int dx, int dy, int x, int y, Cell turn){
        Cell turn1;
        if (turn == Cell.B) turn1 = Cell.W;
        else turn1 = Cell.B;
        int dx1 = sign(dx);
        int dy1 = sign(dy);
        int max = Math.max(Math.abs(dx), Math.abs(dy)) - 1;
        for (int i = 0; i < max; i++) {
            x += dx1;
            y += dy1;
            if (!(cells[x][y] == turn1)) return false;
        }
        return true;
    }
    private int sign(int x){
        if (x < 0) return -1;
        if (x > 0) return 1;
        return 0;
    }

    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("   ");
        for (int i = 1; i < n + 1; i++) {
            sb.append((char) (96 + i)).append(" ");
        }
        sb.append("\n");
        for (int i = 0; i < m; i++) {
            sb.append(8 - i).append("| ");
            for (int j = 0; j < n; j++) {
                if (cells[i][j] == Cell.E){
                    sb.append("*").append(" ");
                } else sb.append(cells[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
