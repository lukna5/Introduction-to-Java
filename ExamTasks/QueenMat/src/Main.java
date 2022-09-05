import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[][] pole = new int[8][8];
        int x = input.nextInt();
        int y = input.nextInt();
        pole[x][y] = 1;
        x = input.nextInt();
        y = input.nextInt();
        pole[x][y] = 2;
        x = input.nextInt();
        y = input.nextInt();
        pole[x][y] = 3;
        CurrentTable(pole);
        new Mat(pole);
    }
    public static void CurrentTable(int[][] pole){
        /*for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pole[i][j] == 1) System.out.print("WK ");
                else if (pole[i][j] == 2) System.out.print("Q ");
                else if (pole[i][j] == 3) System.out.print("BK ");
                else System.out.print("* ");
            }
            System.out.println();
        }

         */
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(pole[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
