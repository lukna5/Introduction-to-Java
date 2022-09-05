import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordStatLineIndex3 {
    public static void main(String[] args) {
        Map<String, StringBuilder> map = new LinkedHashMap<>();
        StringBuilder words;
        StringBuilder entry;
        int letter;
        int quantitywords = 1;
        int quantitystrokes = 1;
        try //(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(args[0])), StandardCharsets.UTF_8)))
            {
            BufferedReader reader = new BufferedReader(new InputStreamReader((System.in)));
            words = new StringBuilder();
            while ((letter = reader.read()) != -1&&letter != 94) {
                if (letter == '\n') {
                    quantitystrokes++;
                    quantitywords = 1;
                } else {
                    if ((Character.getType(letter) == Character.DASH_PUNCTUATION) || (Character.isLetter(letter)) || (letter == '\'')) {
                        words.append(Character.toLowerCase((char) letter));
                    } else if (words.length() != 0) {
                        if (map.get(words.toString()) != null) {
                            StringBuilder new11 = map.get(words.toString()).append(" ").append(quantitystrokes).append(":").append(quantitywords);
                            System.out.println(new11);
                            map.put(words.toString(), new11);
                        } else {
                            entry = new StringBuilder();
                            entry.append(" ").append(quantitystrokes).append(":").append(quantitywords);
                            map.put(words.toString(), entry);
                        }
                        quantitywords++;
                        words.setLength(0);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try
                //BufferedWriter write = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8)))
                 {
            for (Map.Entry<String, StringBuilder> pair : map.entrySet()) {
                int k = 0;
                for (int i = 0; i < pair.getValue().length(); i++){
                    if (pair.getValue().charAt(i) == ':') {
                        k += 1;
                    }
                }
                System.out.print(pair.getKey() + " " + k + pair.getValue().toString() + System.lineSeparator());
                //write.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}