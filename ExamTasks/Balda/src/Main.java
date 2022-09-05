import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)throws IOException {
        Scanner input = new Scanner(System.in);// 10 10 3 4 Б 4 4 а 4 5 л 3 5 т 3 4 и 3 4 б 2 5 и 3 3 м 3 3 с 3 4 м 3 4 б 3 3 м 3 3 с 2 4 м 2 3 о 5 5 д
        int m = input.nextInt();
        int n = input.nextInt();
        String[][] mas = new String[m][n];
        System.out.println("Ввод букв");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mas[i][j] = " ";
            }
        }
        while(input.hasNext()){
            mas[input.nextInt()][input.nextInt()] = input.next().toLowerCase();
            print(mas, m, n);
        }
        new Balda(mas, "C:\\Users\\Андрей\\IdeaProjects\\Balda\\src\\File", m, n);
    }
    public static void print(String[][] mas, int m, int n){
        System.out.print("  ");
        for (int i = 0; i < m; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < m; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < n; j++) {
                if (mas[i][j].equals(" ")) System.out.print(" *");
                else System.out.print(" " + mas[i][j]);
            }
            System.out.println();
        }
    }
}
