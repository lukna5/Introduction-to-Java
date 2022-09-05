import java.util.Scanner;

public class Mat {
    int inf = 0;
    int[][] pole;
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

    public void turnBlack() {
        int[] coordinates = coordinatesBlackKing();
        lastxBK = coordinates[0];
        lastyBK = coordinates[1];
        //System.out.println(lastxBK + " " + lastyBK);
        pole[coordinates[0]][coordinates[1]] = 0;
        pole[input.nextInt()][input.nextInt()] = 3;
    }

    public void turnWhite() {
        System.out.println(stageGame + " stagegame");
        //System.out.println(lastxBK + " " + lastyBK);
        boolean finded = false;
        int[] coordinate = coordinatesBlackKing();
        int xBK = coordinate[0];
        int yBK = coordinate[1];
        coordinate = coordinatesWhiteKing();
        int xWK = coordinate[0];
        int yWK = coordinate[1];
        coordinate = coordinatesQueen();
        int xQ = coordinate[0];
        int yQ = coordinate[1];
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
                System.out.println("ffffgsdgdsggssdg");
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        //System.out.println(onOneLine(i, j, xQ, yQ) + " " +  i + " " + j);
                        if (onOneLine(i, j, xQ, yQ) && (Math.abs(xBK - i) > 1 && Math.abs(yBK - j) > 1) && !finded && (i == xWK || j == yWK)) {
                            //System.out.println(i + " " + j);
                            boolean badTurn = false;
                            int dx = (int) Math.signum(xQ - i);
                            int dy = (int) Math.signum(yQ - j);
                            if (i == xWK){
                                int k = j;
                                while (k != yQ) {
                                    if (pole[xQ][k] == 1) {
                                        badTurn = true;
                                        break;
                                    }
                                    k+= dy;
                                }
                            }
                            if (j == yWK){
                                int k = i;
                                while (k != xQ) {
                                    if (pole[k][yQ] == 1) {
                                        System.out.println("cancel");
                                        badTurn = true;
                                        break;
                                    }
                                    k+= dx;
                                }
                            }
                            if (Math.abs(xQ - i) == Math.abs(yQ - j) ){
                                int k = i;
                                int k1 = j;
                                while (k != xQ){
                                    if (pole[k][k1] == 1){
                                        badTurn = true;
                                        break;
                                    }
                                    k+=dx;
                                    k1+=dy;
                                }
                            }
                            if (badTurn) continue;
                            pole[i][j] = 2;
                            finded = true;
                        }
                    }
                }
            }
            pole[xQ][yQ] = 0;
        } else if(stageGame == 2){
            pole[xQ][yQ] = 0;
            if (!finded){
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (!(i == xQ && j == yQ)) {
                            if (onOneLine(i, j, xQ, yQ) && ((Math.abs(xBK - i) == 2 && Math.abs(yBK - j) == 1) || (Math.abs(xBK - i) == 1 && Math.abs(yBK - j) == 2)) && !finded) {
                                boolean badTurn = false;
                                int dx = (int) Math.signum(xQ - i);
                                int dy = (int) Math.signum(yQ - j);
                                if (i == xWK){
                                    int k = j;
                                    while (k != yQ) {
                                        if (pole[xQ][k] == 1) {
                                            badTurn = true;
                                            break;
                                        }
                                        k+= dy;
                                    }
                                }
                                if (j == yWK){
                                    int k = i;
                                    while (k != xQ) {
                                        if (pole[k][yQ] == 1) {
                                            System.out.println("cancel");
                                            badTurn = true;
                                            break;
                                        }
                                        k+= dx;
                                    }
                                }
                                if (Math.abs(xQ - i) == Math.abs(yQ - j) ){
                                    int k = i;
                                    int k1 = j;
                                    while (k != xQ){
                                        if (pole[k][k1] == 1){
                                            badTurn = true;
                                            break;
                                        }
                                        k+=dx;
                                        k1+=dy;
                                    }
                                }
                                if (badTurn) continue;
                                pole[i][j] = 2;
                                pole[xQ][yQ] = 0; //через короля
                                //System.out.println(i + " CooRdinate" + j);
                                finded = true;
                            }
                        }
                    }
                }
                if (!finded) {
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (onOneLine(i, j, xQ, yQ) && !finded && pole[i][j] == 0 && !((Math.abs(xQ - i) > Math.abs(xWK - i) && yWK == j) || (Math.abs(yQ - j) > Math.abs(yWK - j) && xWK == i)) && !finded) {
                                pole[i][j] = 2;
                                pole[xQ][yQ] = 0;
                                //Main.CurrentTable(pole);
                                if (danger(xBK, yBK) && Math.abs(xBK - i) > 1 && Math.abs(yBK - j) > 1) {
                                    finded = true;
                                    break;
                                }
                                pole[i][j] = 0;
                                pole[xQ][yQ] = 2;
                            }
                        }
                    }
                }
            }
            coordinate = coordinatesQueen();
            xQ = coordinate[0];
            yQ = coordinate[1];
            if (((Math.abs(xBK - xQ) == 2 && Math.abs(yBK - yQ) == 1) || (Math.abs(xBK - xQ) == 1 && Math.abs(yBK - yQ) == 2))) stageGame = 3;
        } else if (stageGame == 3){
            pole[xQ][yQ] = 0;
            System.out.println(lastxBK + " " + lastyBK);
            pole[xQ + xBK - lastxBK][yQ + yBK - lastyBK] = 2;
            if (mat()) {
                pole[xQ][yQ] = 2;
                pole[xQ + xBK - lastxBK][yQ + yBK - lastyBK] = 0;
                stageGame = 4;
            }
        }
        if (stageGame == 4){
            finded = false;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (onOneLine(i, j, xQ, yQ) && (Math.abs(i - xWK) == 1 && Math.abs(j - yWK) == 1) && (Math.abs(i - xBK) == 1 && Math.abs(j - yBK) == 1) && !finded){
                        if (!(mat())) {
                            System.out.println(i + " ne mat " + j);
                            continue;
                        }
                        pole[i][j] = 2;
                        pole[xQ][yQ] = 0;
                        finded = true;
                        System.out.println("Mat");
                    }
                }
            }
            if (!finded){
                int distanceX = Math.abs(xBK - xWK);
                int distanceY = Math.abs(yBK - yWK);
                for (int i = xWK - 1; i < xWK + 2; i++) {
                    for (int j = yWK - 1; j < yWK + 2; j++) {
                        if (i >=0 && i < 8 && j >=0 && j < 8 && !finded){
                            if (((Math.abs(i - xBK) < distanceX && Math.abs(j - yBK) <= distanceY) || Math.abs(j - yBK) < distanceY && Math.abs(i - xBK) <= distanceX) && !(i == xWK && j == yWK)) {
                                if (!(last2xQ == i && last2yQ == j)) {
                                    System.out.println(last2xQ+ " : " + lastxQ);
                                    System.out.println(last2yQ + " : " + lastyQ);
                                    pole[i][j] = 1;
                                    pole[xWK][yWK] = 0;
                                    if (danger(xBK, yBK)) {
                                        System.out.println("danger");
                                        pole[i][j] = 0;
                                        pole[xWK][yWK] = 1;
                                    } else {
                                        last2xQ = lastxQ;
                                        last2yQ = lastyQ;
                                        lastxQ = i;
                                        lastyQ = j;
                                        finded = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!finded){
                stageGame = 1;
                turnWhite();
            }
        }
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

    public boolean mat() {
        int[] coordinates = coordinatesBlackKing();
        int x = coordinates[0];
        int y = coordinates[1];
        for (int i = x - 1; i < x + 2; i++) {
            if (i < 8 && i >= 0) {
                for (int j = y - 1; j < y + 2; j++) {
                    if (j < 8 && j >= 0 && !(i == x && j == y)) {
                        //System.out.println(danger(i, j) + " " + i + " " + j);
                        if (!danger(i, j)) return false;
                    }
                }
            }
        }
        return true;
    }public boolean danger(int x, int y) {
        int[] coordinates = coordinatesWhiteKing();
        int xWK = coordinates[0];
        int yWK = coordinates[1];
        if (Math.abs(x - xWK) < 2 && Math.abs(y - yWK) < 2) return true;
        coordinates = coordinatesQueen();
        int xQ = coordinates[0];
        int yQ = coordinates[1];
        return xQ == x || yQ == y || Math.abs(xQ - x) == Math.abs(yQ - y);
    }



    public boolean toHorse() {
        return false;
    }

    public boolean isHorse() {
        return true;
    }
    public boolean onOneLine(int x1, int y1, int x2, int y2){
        return (x1 == x2 || y1 == y2 || (Math.abs(x1 - x2) == Math.abs(y1 - y2)));
    }

}
