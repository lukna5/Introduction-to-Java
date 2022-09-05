import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Balda {
    String[][] mas;
    Scanner dictionary;
    String file;
    int maxDeep = 10;
    int m;
    int n;
    public Balda(String[][] mas, String file, int m, int n) throws IOException {
        this.mas = mas; // везде либо пробел либо буква
        this.file = file;
        dictionary = new Scanner(new File(file));
        this.m = m;
        this.n = n;
        ArrayList<int[]> bisy = new ArrayList<>();
        String max = "F";
        String max1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!mas[i][j].equals(" ")) {
                    //if (i == 3) System.out.println(j);
                    StringBuilder builder = new StringBuilder(mas[i][j]);
                    max1 = analyze(0, i, j, builder, bisy, false);
                    if (max1.length() > max.length()) max = max1;
                }
            }
        }
        //System.out.println(max.length());
        System.out.println(max + " end ");
    }
    public String analyze(int deep, int x, int y, StringBuilder builder, ArrayList<int[]> bisy, boolean last) throws IOException{
        ArrayList<int[]> bisy1 = copyList(bisy);
        //System.out.println(builder.toString());
        int[] coordinates = new int[2];
        coordinates[0] = x;
        coordinates[1] = y;
        bisy1.add(coordinates);
        if (last){
            //System.out.println(builder);
            String result = last(builder);
            //System.out.println(result);
            //System.out.println();
            //System.out.println(contains(result));
            if (contains(result)) {
                return result;
            } else if (contains(builder.toString())) return builder.toString();
            else return "";
        }
        String best = "";
        if(deep == maxDeep){
            return "";
        }
        for (int i = 0; i < bisy1.size(); i++) {
            //System.out.println(bisy1.get(i)[0] + " " + bisy1.get(i)[1] + " first");
        }
        if (x > 0 && !was(x - 1, y, bisy1)) {
            StringBuilder dopBuilder = new StringBuilder(builder);
            if (mas[x - 1][y].equals(" ")){
                last = true;
            }
            else dopBuilder.append(mas[x - 1][y]);
            if (has(dopBuilder.toString())) {
                //System.out.println(mas[x][y] + " " + x + " " + y + " " + deep);
                //System.out.println(has(dopBuilder.toString()) + " " + (x - 1) + " " + (y) + " " + dopBuilder + " " + last);
                best = better(best, analyze(deep + 1, x - 1, y, dopBuilder, bisy1, last));
            }
            last = false;
        }
        for (int i = 0; i < bisy1.size(); i++) {
            //System.out.println(bisy1.get(i)[0] + " " + bisy1.get(i)[1]);
        }
        //System.out.println(" next");
        if (y > 0 && !was(x, y - 1, bisy1)){
            StringBuilder dopBuilder = new StringBuilder(builder);
            if (mas[x][y - 1].equals(" ")){
                last = true;
            }
            else dopBuilder.append(mas[x][y - 1]);
            if (has(dopBuilder.toString())) {
                //System.out.println(mas[x][y] + " " + x + " " + y + " " + deep);
                //System.out.println(has(dopBuilder.toString()) + " " + (x) + " " + (y - 1) + " " + dopBuilder + " " + last);
                best = better(best, analyze(deep + 1, x, y - 1, dopBuilder, bisy1, last));
            }
            last = false;
        }
        if (x < n - 1 && !was(x + 1, y, bisy1)){
            StringBuilder dopBuilder = new StringBuilder(builder);
            if (mas[x + 1][y].equals(" ")){
                last = true;
            }
            else dopBuilder.append(mas[x + 1][y]);
            if (has(dopBuilder.toString())) {
                //System.out.println(mas[x][y] + " " + x + " " + y + " " + deep);
                //System.out.println(has(dopBuilder.toString()) + " " + (x + 1) + " " + (y) + " " + dopBuilder + " " + last);
                best = better(best, analyze(deep + 1, x + 1, y, dopBuilder, bisy1, last));
            }
            last = false;
        }
        if (y < m - 1 && !was(x, y + 1, bisy1)){
            StringBuilder dopBuilder = new StringBuilder(builder);
            //System.out.println(dopBuilder);
            if (mas[x][y + 1].equals(" ")){
                last = true;
            }
            else dopBuilder.append(mas[x][y + 1]);
            //System.out.println(dopBuilder + "FFFFFFFFFFFFFFFFFFFFFF");
            //System.out.println(has(dopBuilder.toString()));
            if (has(dopBuilder.toString())) {
                //System.out.println(mas[x][y] + " " + x + " " + y + " " + deep);
                //System.out.println(has(dopBuilder.toString()) + " " + (x) + " " + (y + 1) + " " + dopBuilder + " " + last);
                best = better(best, analyze(deep + 1, x, y + 1, dopBuilder, bisy1, last));
            }
            last = false;
        }
        return best;
    }
    private boolean contains(String word) throws IOException{
        update();
        while (dictionary.hasNext()){
            String next = dictionary.next();
            if (next.equals(word)) {
                update();
                return true;
            }
        }
        update();
        return false;
    }
    private boolean has(String word) throws IOException{
        update();
        //System.out.println(word);
        //System.out.println((int) word.charAt(word.length() - 1));
        if (word.charAt(word.length() - 1) == 32) word = word.substring(0, word.length() - 1);
        while (dictionary.hasNext()){
            String next = dictionary.next();
            //System.out.print(next + " ");
            for (int i = 0; i < next.length() - word.length(); i++) {
                StringBuilder builder = new StringBuilder(next);
                StringBuilder builder1;
                builder1 = new StringBuilder(builder.substring(i, word.length() + i).toString());
                if (word.equals("ба")){
                    //System.out.println(builder1);
                    //System.out.println(builder + " билдер");
                }
                if (word.equals(builder1.toString())){
                    update();
                    return true;
                }
            }
        }
        //System.out.println();
        update();
        return false;
    }
    private boolean was(int x, int y, ArrayList<int[]> bisy)throws IOException{
        for (int i = 0; i < bisy.size(); i++) {
            if (bisy.get(i)[0] == x && bisy.get(i)[1] == y){
                update();
                return true;
            }
        }
        update();
        return false;
    }
    private String better(String first, String second){
        if (first.length() < second.length()) return second;
        else return first;
    }
    private String last(StringBuilder builder) throws IOException{
        update();
        //System.out.println(builder);
        while (dictionary.hasNext()){
            StringBuilder next = new StringBuilder(dictionary.next());
            //System.out.println(next);
            int size = next.length();
            String lastSymb = Character.toString(next.charAt(size - 1));
            next.setLength(next.length()-1);
            if (size == builder.length() + 1 && identic(builder, next)) {
                next.append(lastSymb);
                //System.out.println(true);
                //System.out.println(next);
                update();
                return next.toString();
            }
        }
        update();
        //System.out.println(false);
        return builder.toString();
    }
    private void update() throws IOException{
        dictionary = new Scanner(new File(file));
    }
    private boolean identic(StringBuilder first, StringBuilder second){
        for (int i = 0; i < first.length(); i++) {
            String symb1 = Character.toString(first.charAt(i)).toLowerCase();
            String symb2 = Character.toString(second.charAt(i)).toLowerCase();
            if (!symb1.equals(symb2)) return false;
        }
        return true;
    }
    public ArrayList<int[]> copyList(ArrayList<int[]> list){
        ArrayList<int[]> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int[] coord = new int[2];
            coord[0] = list.get(i)[0];
            coord[1] = list.get(i)[1];
            res.add(coord);
        }
        return res;
    }
}
