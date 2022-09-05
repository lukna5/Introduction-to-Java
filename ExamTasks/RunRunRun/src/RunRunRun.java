import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class RunRunRun {
    public static void main(String[] args) throws IOException {
        //BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        int n = Integer.parseInt(line);
        int[][] mas = new int[n][n];
            for (int i = 0; i<n; i++) {
                line = input.nextLine();
                int j = 0;
                while (j<line.length()) {
                    mas[i][j]=Integer.parseInt(line.substring(j, j+1));
                    j++;
                }
            }
            for (int i = 0; i<n;i++){
                for (int j = i+1;j<n;j++){
                    for (int k = j+1; k<n;k++){
                        if (mas[i][j]!=0) {
                            mas[i][k]=(mas[i][k]-mas[j][k])%10;
                        }
                    }
                }
            }

            for(int i = 0; i<n; i++){
                for (int j = 0; j<n; j++) System.out.print(mas[i][j]);
                System.out.println();
            }
        }

}
