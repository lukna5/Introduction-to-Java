import java.util.Scanner;

public class Mat {
    int inf = 0;
    int[][] pole;
    public int xQ;
    public int yQ;
    public int xBK;
    public int yBK;
    public int xWK;
    public int yWK;
    int lastxQ = 0;
    int lastyQ = 0;
    int last2xQ = 0;
    int last2yQ = 0;
    int lastxBK = 0;
    int lastyBK = 0;
    int stageGame = 1;
    Scanner input = new Scanner(System.in);


    public Mat(int[][] pole) {
        this.pole = pole;
        int[] coordinate = coordinatesBlackKing();
        int xBK = coordinate[0];
        int yBK = coordinate[1];
        coordinate = coordinatesWhiteKing();
        int xWK = coordinate[0];
        int yWK = coordinate[1];
        coordinate = coordinatesQueen();
        int xQ = coordinate[0];
        int yQ = coordinate[1];
        lastxQ = xQ;
        lastyQ = yQ;
        last2xQ = xQ;
        last2yQ = yQ;
        lastxBK = xBK;
        lastyBK = yBK;
        //System.out.println(lastxBK + " " + lastyBK);
        if (Math.abs(xQ - xWK) == 1 || Math.abs(yQ - yWK) == 1) stageGame = 2;
        if (((Math.abs(xBK - xQ) == 2 && Math.abs(yBK - yQ) == 1) || (Math.abs(xBK - xQ) == 1 && Math.abs(yBK - yQ) == 2))) stageGame = 3;
        game();
    }
    public void turnBlack() {
        int[] coordinates = coordinatesBlackKing();
        lastxBK = coordinates[0];
        lastyBK = coordinates[1];
        //System.out.println(lastxBK + " " + lastyBK);
        pole[coordinates[0]][coordinates[1]] = 0;
        pole[input.nextInt()][input.nextInt()] = 3;
    }
    public void game() {
        while (true) {
            turnWhite();
            Main.CurrentTable(pole);
            if (mat()) {
                Main.CurrentTable(pole);
                break;
            }
            turnBlack();
            Main.CurrentTable(pole);
        }
        System.out.println("GG");
    }
    public void turnWhite() {
        System.out.println(stageGame + " stagegame");
        //System.out.println(lastxBK + " " + lastyBK);
        boolean finded = false;
        if (stageGame == 1) {
            if (xWK == xQ) {
                System.out.println("x");
                if (yWK > yQ) pole[xWK][yWK - 1] = 2;
                else if (yWK < yQ) pole[xWK][yWK + 1] = 2;
                stageGame = 2;
            } else if (yWK == yQ) {
                System.out.println("y");
                if (xWK > xQ) pole[xWK - 1][yWK] = 2;
                else pole[xWK + 1][yWK] = 2;
                stageGame = 2;
            } else {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        //System.out.println(onOneLine(i, j, xQ, yQ) + " " +  i + " " + j);
                        if (onOneLine(i, j, xQ, yQ) && (Math.abs(xBK - i) > 1 && Math.abs(yBK - j) > 1) && !finded && (i == xWK || j == yWK) && correctMove(i, j)) {
                            //System.out.println(i + " " + j);
                            pole[i][j] = 2;
                            finded = true;
                        }
                    }
                }
            }
            pole[xQ][yQ] = 0;
        } else if (stageGame == 2) {
            pole[xQ][yQ] = 0;
            if (!finded) {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (pole[i][j] == 0) {
                            if (onOneLine(i, j, xQ, yQ) && ((Math.abs(xBK - i) == 2 && Math.abs(yBK - j) == 1) || (Math.abs(xBK - i) == 1 && Math.abs(yBK - j) == 2)) && !finded && correctMove(i, j)) {
                                changeXQ(i, j);
                                finded = true;
                            }
                        }
                    }
                }
            }
            if (!finded) {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (onOneLine(i, j, xQ, yQ) && !finded && pole[i][j] == 0 && correctMove(i, j)) {
                            int xQ1 = xQ;
                            int yQ1 = yQ;
                            changeXQ(i, j);
                            //Main.CurrentTable(pole);
                            if (dangerForKing(xBK, yBK) && Math.abs(xBK - i) > 1 && Math.abs(yBK - j) > 1) {
                                finded = true;
                                break;
                            }
                            changeXQ( xQ1, yQ1);
                        }
                    }
                }
            }
            if (((Math.abs(xBK - xQ) == 2 && Math.abs(yBK - yQ) == 1) || (Math.abs(xBK - xQ) == 1 && Math.abs(yBK - yQ) == 2)))
                stageGame = 3;
        }
    }
    public boolean mat() {
        for (int i = xBK - 1; i < xBK + 2; i++) {
            if (i < 8 && i >= 0) {
                for (int j = yBK - 1; j < yBK + 2; j++) {
                    if (j < 8 && j >= 0 && !(i == xBK && j == yBK)) {
                        //System.out.println(danger(i, j) + " " + i + " " + j);
                        if (!dangerForKing(i, j)) return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean nearWhiteKing(int x, int y){
        return Math.abs(x - xWK) < 2 && Math.abs(y - yWK) < 2;
    }

    private boolean correctMove(int x, int y){
        int dx = (int) Math.signum(xQ - x);
        int dy = (int) Math.signum(yQ - y);
        while (x != xQ && y != yQ){
            if (pole[x][y] != 0 || pole[x][y] != 2) return false;
            x+= dx;
            y+= dy;
        }
        return Math.abs(x - xWK) < 2 && Math.abs(y - yWK) < 2;
    }

    private boolean nearBlackKing(int x, int y){
        return Math.abs(x - xBK) < 2 && Math.abs(y - yBK) < 2;
    }
    private void changeXQ(int x, int y){
        pole[x][y] = 2;
        xQ = x;
        yQ = y;
    }

    private void changeBK(int x, int y){
        pole[x][y] = 3;
        xBK = x;
        yBK = y;
    }

    private void changeWK(int x, int y){
        pole[x][y] = 1;
        xWK = x;
        yWK = y;
    }

    public boolean dangerForKing(int x, int y) {
        if (Math.abs(x - xWK) < 2 && Math.abs(y - yWK) < 2) return true;
        return xQ == x || yQ == y || Math.abs(xQ - x) == Math.abs(yQ - y);
    }

    public boolean onOneLine(int x1, int y1, int x2, int y2){
        return (x1 == x2 || y1 == y2 || (Math.abs(x1 - x2) == Math.abs(y1 - y2)));
    }

    public int[] coordinatesBlackKing() {
        int[] result = new int[2];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pole[i][j] == 3) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return null;
    }

    public int[] coordinatesQueen() {
        int[] result = new int[2];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pole[i][j] == 2) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return null;
    }

    public int[] coordinatesWhiteKing() {
        int[] result = new int[2];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pole[i][j] == 1) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return null;
    }
}
