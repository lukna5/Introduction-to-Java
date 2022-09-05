import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Sort {
    private HashMap<String, Integer> dict = new HashMap<>();
    private boolean[] commands = new boolean[8];
    public Sort(ArrayList<String> conditions, Scanner input) {
        dict.put("-b", 1);
        dict.put("-d", 2);
        dict.put("-f", 3);
        dict.put("-i", 4);
        dict.put("-n", 5);
        dict.put("-r", 6);
        for (int i = 0; i < conditions.size(); i++) {
            commands[dict.get(conditions.get(i))] = true;
        }
        commands[7] = true;
        for (int i = 0; i < 6; i++) {
            System.out.print(commands[i]);
        }
        System.out.println();
        System.out.println(correctStroke(input.nextLine()));
        ArrayList<String> list = new ArrayList<>();
        list.sort();
    }
    private String correctStroke(String text){ // как выглядит строка в истинном виде для сортировки
        boolean start = true;
        boolean correct = true;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char next = text.charAt(i);
            //System.out.println(next  + " " + i + "     " + (int) next);
            char next1 = 0;
            for (int j = 1; j < 8; j++) {
                //System.out.print(next + "I");
                if (commands[j]){
                    //System.out.println(next + " " + next1 + " for" + j);
                    if (j == 1 && start){
                        while (text.charAt(i) == 32) {
                            i++;
                        }
                        i--;
                        //System.out.println(i);
                        start = false;
                        //System.out.println(next +  "    1 " + (int) next);
                        break;
                    }
                    if (j == 2){
                        if (!(next > 47 && next < 58 || next > 64 && next < 91 || next > 96 && next < 123)) break;
                    }
                    if (j == 3) {
                        next = Character.toString(next).toLowerCase().charAt(0);
                        //System.out.println(next +  "    3 " + (int) next);
                    }
                    if (j == 4){
                        if (!(next > 31 && next < 256)) break;
                    }
                    if (j == 5) {
                        //System.out.println("F5");
                        if (!(next > 47 && next < 58)) break;
                    }
                    if (j == 7) {
                        //System.out.println(next + " next");
                        next1 = next;
                        break;
                    }
                    //System.out.println(j);
                    //System.out.println(next + " " + next1);
                }
            }
            //System.out.println(next1);
            builder.append(next1);
        }
        return builder.toString();
    }
}
