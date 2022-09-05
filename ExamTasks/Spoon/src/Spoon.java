import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Spoon {
    BufferedReader reader;
    private int[] mas;
    private int sizeMas;
    private int limit;
    private int size;
    private int index = 0;
    private int ind = 0;
    private boolean stop = false;
    private final HashMap<String, Integer> rules = new HashMap<>();
    private ArrayList<Integer> commands;
    public LinkedList<Integer> stack = new LinkedList<>();
    public Spoon(String sizeMas, String limit, BufferedReader reader) throws IOException {
        this.limit = Integer.parseInt(limit);
        rules.put("1", 1);
        rules.put("000", 2);
        rules.put("010", 3);
        rules.put("011", 4);
        rules.put("00100", 5);
        rules.put("0011", 6);
        rules.put("0010110", 7);
        rules.put("001010", 8);
        this.sizeMas = Integer.parseInt(sizeMas);
        System.out.println(sizeMas);
        this.reader = reader;
        String command = reader.readLine();
        commands = split(command);
        size = commands.size();
        mas = new int[this.sizeMas];
        String next;
        while (ind < size && !stop) {
            int com = nextCommand();
            useCommand(com);
        }
        if (!stop) System.out.println("Too much operations");

    }

    private ArrayList<Integer> split(String command) {
        int num = 0;
        int count = 0;
        ArrayList<Integer> res = new ArrayList<>();
        while (num < command.length()) {
            StringBuilder builder = new StringBuilder();
            while (!rules.containsKey(builder.toString()) && num < command.length()) {
                if (command.charAt(num) == 48 || command.charAt(num) == 49) {
                    builder.append((int) command.charAt(num) - 48);
                }
                num++;
            }
            if (count == limit){
                stop = true;
                break;
            }
            count++;
            res.add(rules.get(builder.toString()));
        }
        return res;
    }
    public int nextCommand() {
        if (!stop) {
            int res = commands.get(ind);
            ind++;
            return res;
        }
        return 9;
    }
    public void add(){
        mas[index]++;
    }
    public void subtract(){
        mas[index]--;
    }
    public void goRight(){
        index = (index + 1) % sizeMas;
    }
    public void goLeft(){
        if (index == 0) index = size-1;
        else index--;
    }
    public void start(int i) throws IOException {
        int com;
        while (!stop){
            com = nextCommand();
            if (com == 6){
                if (mas[i] == 0) {
                    stack.removeLast();
                    break;
                }
                else {
                    ind = stack.getLast();
                }
            }
            useCommand(com);
        }
    }
    public void out(){
        System.out.print((char) (mas[index]));
    }
    public void useCommand(int x) throws IOException{
        LinkedList<ArrayList<Integer>> listOfFor = new LinkedList<>();
        switch (x){
            case 1: add();
            break;
            case 2: subtract();
            break;
            case 3: goRight();
            break;
            case 4: goLeft();
            break;
            case 5:
                stack.add(ind);
                start(ind);
                break;
            case 7: mas[index] = Character.getNumericValue(reader.read());
            break;
            case 8:
                out();
                break;
            case 9: stop = true;
        }
    }
}
