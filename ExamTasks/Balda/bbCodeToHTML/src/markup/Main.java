package markup;

import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args)throws IOException {
        StringBuilder builder = new StringBuilder("[u][s] [b] vfv[/u]  b   [/b][/s]    text");
        BBCode Shit = new BBCode();
        System.out.println(Shit.toHtml(builder));
        /*BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Андрей\\IdeaProjects\\bbCodeToHTML\\src\\markup\\Input"));
        StringBuilder text = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            text.append(line);
        }
        BBCode realizate = new BBCode();
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Андрей\\IdeaProjects\\bbCodeToHTML\\Html.html"));
        writer.write(realizate.toHtml(text));
        writer.close();

         */
    }
}
