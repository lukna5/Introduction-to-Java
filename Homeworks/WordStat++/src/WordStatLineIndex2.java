import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordStatLineIndex2 {
    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<>();
        StringBuilder words;
        StringBuilder entry;
        int letter;
        int quantitywords = 1;
        int quantitystrokes = 1;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(args[0])), StandardCharsets.UTF_8))) {
            words = new StringBuilder();
            while ((letter = reader.read()) != -1) {
                if (letter == '\n') {
                    quantitystrokes++;
                    quantitywords = 1;
                } else {
                    if ((Character.getType(letter) == Character.DASH_PUNCTUATION) || (Character.isLetter(letter)) || (letter == '\'')) {
                        words.append(Character.toLowerCase((char) letter));
                    } else if (words.length() != 0) {
                        if (map.get(words.toString()) != null) {
                            map.put(words.toString(), map.get(words.toString() + " " + quantitystrokes + ":" + quantitywords));
                        } else {
                            entry = new StringBuilder();
                            entry.append(" ").append(quantitystrokes).append(":").append(quantitywords);
                         //   map.put(words.toString(), " " + quantitystrokes + ":" + quantitywords);
                            map.put(words.toString(), " " + quantitystrokes + ":" + quantitywords);

                        }
                        quantitywords++;
                        words.setLength(0);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try (
                BufferedWriter write = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
            for (Map.Entry<String, String> pair : map.entrySet()) {
                int k = 0;
                for (int i = 0; i < pair.getValue().length() + 1; i++){
                    if ((pair.getValue().charAt(i))== ':') {
                        k += 1;
                    }
                }
                write.write(pair.getKey() + " " + k + pair.getValue() + System.lineSeparator());
                write.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}