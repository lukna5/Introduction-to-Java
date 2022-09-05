package markup;


import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Paragraph paragraph = new Paragraph(List.of(
                new Strong(List.of(
                        new Text("1"),
                        new Strikeout(List.of(
                                new Text("2"),
                                new Emphasis(List.of(
                                        new Text("3"),
                                        new Text("4")
                                )),
                                new Text("5")
                        )),
                        new Text("6")
                ))
        ));
        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();
        StringBuilder builder = new StringBuilder();
        if (command.equals("Md")|| command.equals("Markdown") || command.equals("toMarkdown")){
            paragraph.toMarkdown(builder);
        }
        else{
            paragraph.toHtml(builder);
        }
        System.out.println(builder);
    }
}