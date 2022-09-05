import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[][] pole = new int[10][10];
        Scanner input = new Scanner(System.in);
        String command = input.next();
        int number;
        int shet = 0;
        int stolb = 0;
        int strok = 0;
        int what;
        int count = 0;
        while (!command.equals("end")) {
            number = Integer.parseInt(command);
            shet = (shet + 1) % 3;
            if (shet == 1) strok = number;
            else if (shet == 2) stolb = number;
            else {
                what = number;
                if (pole[strok][stolb] == 0) count++;
                pole[strok][stolb] = what;
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        System.out.print(pole[i][j] + " ");
                    }
                    System.out.println();
                }
            }
            command = input.next();
        }
        if (Correct(pole)) new Sudoka(pole, count);
        else System.out.println("Такой Судоки нет");
    }
    public static boolean Correct(int[][] pole){
        int[] chisla = new int[10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                chisla[pole[i][j]]++;
            }
            for (int j = 1; j < 10; j++) {
                if (chisla[j] > 1){
                    return false;
                }
            }
            update(chisla);
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                chisla[pole[j][i]]++;
            }
            for (int j = 1; j < 10; j++) {
                if (chisla[j] > 1){
                    return false;
                }
            }
            update(chisla);
        }
        for (int strokkv = 0; strokkv < 3; strokkv++) {
            for (int stolbkv = 0; stolbkv < 3; stolbkv++) {
                for (int stroka = strokkv * 3; stroka < strokkv * 3 + 3; stroka++) {
                    for (int stolb = stolbkv * 3; stolb < stolbkv * 3 + 3; stolb++) {
                        chisla[pole[stroka][stolb]]++;
                    }
                }
                for (int j = 1; j < 10; j++) {
                    if (chisla[j] > 1){
                        return false;
                    }
                }
                update(chisla);
            }
        }
        return true;
    }
    public static void update(int[] chisla){
        for (int i = 0; i < 10; i++) {
            chisla[i] = 0;
        }
    }
}
