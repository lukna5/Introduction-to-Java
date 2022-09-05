import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordStatLineIndex {
    public static void main(String[] args) {
        Map<String, ArrayList<String>> map = new LinkedHashMap<>();
        StringBuilder words;
        int letter;
        int quantity_words = 1;
        int quantity_strokes = 1;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(args[0])), StandardCharsets.UTF_8))) {
            words = new StringBuilder();
            while ((letter = reader.read()) != -1) {
                if (letter == '\n') {
                    quantity_strokes++;
                    quantity_words = 1;
                } else {
                    if ((Character.getType(letter) == Character.DASH_PUNCTUATION) || (Character.isLetter(letter)) || (letter == '\'')) {
                        words.append(Character.toLowerCase((char) letter));
                    } else if (words.length() != 0) {
                        if (map.get(words.toString()) == null) {
                            map.put((words.toString()), new ArrayList<>());
                        }
                        map.get(words.toString()).add(" " + quantity_strokes + ":" + quantity_words);
                        quantity_words++;
                        words.setLength(0);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (
                BufferedWriter write = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
            for (Map.Entry<String, ArrayList<String>> pair : map.entrySet()) {
                int k = 0;
                for (int i = 0; i < pair.getValue().size(); i++){
                    k += 1;
                }
                write.write(pair.getKey() + " " + k);
                for (int i = 0; i < pair.getValue().size(); i++) {
                    write.write(pair.getValue().get(i));
                }
                write.write(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}