import java.util.ArrayList;

public class Sudoka {
    int[][] pole;
    int count;
    public Sudoka(int[][] pole, int count) {
        this.pole = pole;
        this.count = count;
        pole[9][9] = count;
        pole = find(pole);
        //System.out.println(count);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(pole[i][j] + " ");
            }
            System.out.println();
        }
    }
    public int[][] find(int[][] table){
        ArrayList[][] potent = new ArrayList[9][9];
        int minstrok = 0;
        int minstolb = 0;
        int min = 11;
        if (table[9][9] == 81){
            if (pole[9][1] == 1) return table;
            else if (Main.Correct(table)) pole[9][2] = 1;
            return table;
        }
        if (Main.Correct(pole)){
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    potent[i][j] = list(table, i, j);
                    if (potent[i][j].size() < min && table[i][j] == 0){
                        min = potent[i][j].size();
                        minstrok = i;
                        minstolb = j;
                    }
                }
            }
            table[9][9]++;
            //System.out.println(potent[minstrok][minstolb].size() + " " + min + " " + minstrok + " : " + minstolb);
            int[][] table1;
            for (int i = 0; i < potent[minstrok][minstolb].size(); i++) {
                table[minstrok][minstolb] = (int) potent[minstrok][minstolb].get(i);
                table1 = find(table);
                if (table1[9][2] == 1) return table1;
                table[minstrok][minstolb] = 0;
            }
        }
        System.out.println("table");
        return table;
    }
    public ArrayList<Integer> list(int[][] pole, int strok, int stolb){
        ArrayList<Integer> res = new ArrayList<>();
        int[] have = new int[10];
        int startstrok = strok - strok%3;
        int startstolb = stolb - stolb%3;
        for (int i = 0; i < 9; i++) {
            have[pole[strok][i]]++;
            have[pole[i][stolb]]++;
        }
        for (int i = startstrok; i < startstrok + 3; i++) {
            for (int j = startstolb; j < startstolb + 3; j++) {
                have[pole[i][j]]++;
            }
        }
        for (int i = 1; i < 10; i++) {
            if (have[i] == 0){
                //System.out.print(i + " i ");
                res.add(i);
            }
        }
        //System.out.println();
        return res;
    }
}
