import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        HashMap<String, String> dictionary = new HashMap<>();
        BufferedReader input = new BufferedReader(new FileReader( "C:\\Users\\Андрей\\IdeaProjects\\Шаблонизатор\\src\\Input"));
        BufferedReader readerDict = new BufferedReader(new FileReader("C:\\Users\\Андрей\\IdeaProjects\\Шаблонизатор\\src\\Dictionary"));
        String line;

        while ((line = readerDict.readLine()) != null){
            StringBuilder builder = new StringBuilder(line);
            StringBuilder suspect = new StringBuilder();
            StringBuilder value = new StringBuilder();
            int i = 0;
            while(builder.charAt(i) != 61){
                suspect.append(builder.charAt(i));
                i++;
            }
            i++;
            for (int j = i; j < builder.length(); j++) {
                value.append(builder.charAt(j));
            }
            if (dictionary.containsKey(suspect.toString())){
                if (dictionary.get(suspect.toString()).length() < value.length()) dictionary.replace(suspect.toString(), value.toString());
            } else dictionary.put(suspect.toString(), value.toString());
        }
        StringBuilder newText = new StringBuilder();
        StringBuilder next = new StringBuilder();
        int charSymb;
        String symb;
        while ((charSymb = input.read()) != -1){
            symb = Character.toString(charSymb);
            next.append(symb);
            int index = contain(dictionary, next);
            if (index != -1){
                newText.append(next.substring(0, index)).append(dictionary.get(next.substring(index, next.length())));
                next.setLength(0);
            }
        }
        newText.append(next);
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("C:\\Users\\Андрей\\IdeaProjects\\Шаблонизатор\\src\\Input")));
        writer.write(newText.toString());
        writer.close();

    }
    private static int contain(HashMap<String, String> dictionary, StringBuilder word){
        for (int i = 0; i < word.length(); i++) {
            if (dictionary.containsKey(word.substring(i, word.length()))) return i;
        }
        return -1;
    }
}
