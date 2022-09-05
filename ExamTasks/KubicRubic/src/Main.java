import java.util.*;

public class Main {
    static String[][][] cubik = new String[6][3][3];
    static HashMap<String, Integer> map = new HashMap<>();
    static ArrayList<List<List<Integer>>> combinations = new ArrayList<>();
    public static void main(String[] args) {
        combinations.add(cC(1, 2, 1, 4, 2, 1, 3, 2, 1, 2, 2, 1));
        combinations.add(cC(0, 1, 1, 2, 1, 1, 5, 1, 1, 4, 1, 3));
        combinations.add(cC(0, 2, 3, 3, 1, 1, 5, 2, 1, 1, 1, 3));
        combinations.add(cC(0, 1, 3, 4, 1, 1, 5, 1, 3, 2, 1, 3));
        combinations.add(cC(0, 2, 1, 1, 1, 1, 5, 2, 3, 4, 1, 3));
        combinations.add(cC(1, 2, 3, 2, 2, 3, 3, 2, 3, 4, 2, 3));
        int storona;
        //combinations.add();
        map.put("U", 0);
        map.put("L", 1);    
        map.put("F", 2);
        map.put("R", 3);
        map.put("B", 4);
        map.put("D", 5);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (i == 0) cubik[i][j][k] = "W";
                    if (i == 1) cubik[i][j][k] = "R";
                    if (i == 2) cubik[i][j][k] = "B";
                    if (i == 3) cubik[i][j][k] = "O";
                    if (i == 4) cubik[i][j][k] = "G";
                    if (i == 5) cubik[i][j][k] = "Y";
                }
            }
        }
        showPosition();
        Scanner input = new Scanner(System.in);
        String command = input.next();
        while (!command.equals("end")) {
            StringBuilder builder = new StringBuilder(command);
            if (command.length() > 0) {
                storona = map.get(Character.toString(builder.charAt(0)));
                boolean reverse = false;
                if (command.length() > 1 && command.charAt(1) == '`') {
                    reverse = true;
                }
                workWithStorona(storona);
                workWithCube(combinations.get(storona), reverse);
                showPosition();
            }
            command = input.next();
        }
    }
    public static void showPosition(){
        for (int j = 0; j < 3; j++) {
            System.out.print("      ");
            for (int k = 0; k < 3; k++) {
                System.out.print(cubik[0][j][k] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print(cubik[1 + j][i][k] + " ");
                }
            }
            System.out.println();
        }
        for (int i = 0; i < 3; i++) {
            System.out.print("      ");
            for (int j = 0; j < 3; j++) {
                System.out.print(cubik[5][i][j] + " ");
            }
            System.out.println();
        }
    }
    public static List<List<Integer>> cC(int x1, int y1, int a1, int x2, int y2, int a2, int x3, int y3, int a3, int x4, int y4, int a4){
        List<List<Integer>> list = new ArrayList<>();
        list.add(cC(x1, y1, a1));
        list.add(cC(x2, y2, a2));
        list.add(cC(x3, y3, a3));
        list.add(cC(x4, y4, a4));
        return list;
    }
    public static List<Integer> cC(int x, int y, int a){
        List<Integer> list;
        list = Arrays.asList(x, y, a);
        return list;
    }
    public static void workWithStorona(int storona){
        String last = cubik[storona][1][0];
        String last2 = cubik[storona][2][0];
        String next;
        for (int i = 0; i < 3; i++) {
            next = last2;
            last2 = last;
            last = cubik[storona][0][i];
            cubik[storona][0][i] = next;
        }
        next = last2;
        last2 = last;
        last = cubik[storona][1][2];
        cubik[storona][1][2] = next;
        for (int i = 2; i >= 0; i--) {
            next = last2;
            last2 = last;
            last = cubik[storona][2][i];
            cubik[storona][2][i] = next;
        }
        next = last2;
        cubik[storona][1][0] = next;
    }
    public static void workWithCube(List<List<Integer>> list, boolean reverse){
        List<List<Integer>> list1 = new ArrayList<>();
        if (reverse){
            for (int i = 3; i >= 0; i--) {
                list1.add(list.get(i));
            }
        } else list1 = list;
        List<Integer> inf;
        inf = list1.get(3);
        String x0;
        String x1;
        String x2;
        String lastx0;
        String lastx1;
        String lastx2;
        if (inf.get(1) == 1){
            x0 = cubik[inf.get(0)][0][inf.get(2) - 1];
            x1 = cubik[inf.get(0)][1][inf.get(2) - 1];
            x2 = cubik[inf.get(0)][2][inf.get(2) - 1];
        } else{
            x0 = cubik[inf.get(0)][inf.get(2) - 1][0];
            x1 = cubik[inf.get(0)][inf.get(2) - 1][1];
            x2 = cubik[inf.get(0)][inf.get(2) - 1][2];
        }
        for (int i = 0; i < 4; i++) {
            inf = list1.get(i);
            lastx0 = x0;
            lastx1 = x1;
            lastx2 = x2;
            //System.out.println(lastx0 + " " + lastx1 + " " + lastx2);
            if (inf.get(1) == 1){
                x0 = cubik[inf.get(0)][0][inf.get(2) - 1];
                x1 = cubik[inf.get(0)][1][inf.get(2) - 1];
                x2 = cubik[inf.get(0)][2][inf.get(2) - 1];
                cubik[inf.get(0)][0][inf.get(2) - 1] = lastx0;
                cubik[inf.get(0)][1][inf.get(2) - 1] = lastx1;
                cubik[inf.get(0)][2][inf.get(2) - 1] = lastx2;
            } else{
                x0 = cubik[inf.get(0)][inf.get(2) - 1][0];
                x1 = cubik[inf.get(0)][inf.get(2) - 1][1];
                x2 = cubik[inf.get(0)][inf.get(2) - 1][2];
                cubik[inf.get(0)][inf.get(2) - 1][0] = lastx0;
                cubik[inf.get(0)][inf.get(2) - 1][1] = lastx1;
                cubik[inf.get(0)][inf.get(2) - 1][2] = lastx2;
            }
        }
    }
}
