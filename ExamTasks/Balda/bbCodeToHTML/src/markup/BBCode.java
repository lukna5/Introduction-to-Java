package markup;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
public class BBCode {
    private int deep[] = new int[4];
    private int index = 0;
    String strong;
    String crossed;
    String italics;
    String underlined;
    String input;
    String left;
    String right;
    private boolean isText;
    HashMap<String, String> map = new HashMap<>();
    public BBCode(){
        strong = "[b]";
        crossed = "[s]";
        italics = "[i]";
        underlined = "[u]";
        left = "[";
        right = "]";
        map.put(strong, "1");
        map.put(crossed, "2");
        map.put(italics, "3");
        map.put(underlined, "4");
        map.put(strong.charAt(0) + "/" + strong.substring(1), "5");
        map.put(crossed.charAt(0) + "/" + crossed.substring(1), "6");
        map.put(italics.charAt(0) + "/" + italics.substring(1), "7");
        map.put(underlined.charAt(0) + "/" + underlined.substring(1), "8");

    }
    private char getNext(){
        index++;
        return input.charAt(index - 1);
    }
    public String toHtml(StringBuilder input) {
        this.input = input.toString();
        int index1 = 0;
        List<Markup> list = new ArrayList<>();
        /*Paragraph paragraph = new Paragraph(List.of(
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

         */
        StringBuilder builder = new StringBuilder();
        Markup markup = recurs(0).get(0);
        //System.out.println(markup + "mark");
        markup.convert(builder);
        return (builder.toString());
    }
    private List<Markup> recurs(int[] deep1){
        //System.out.println("Again recurs");
        deep++;
        List<Markup> list = new ArrayList<>();
        if (index < input.length() - 1){
            //System.out.println(deep + " " + deep1 + " ddddeep");
            while (deep != deep1 && index < input.length()) {
                //System.out.println(list);
                String next = getNextCommand();
                System.out.println(next + " command");
                System.out.println(index + " index");
                if (isText) list.add(new Text(next));
                else if (next.length() == 1 && Integer.parseInt(next) > 4) deep[Integer.parseInt(next)]--;
                else if (next.length() == 1){
                    //System.out.println(deep + " deep");
                    Markup markup = choose(Integer.parseInt(next));
                    StringBuilder builder = new StringBuilder();
                    markup.convert(builder);
                    //System.out.println(builder+ " builder");
                    list.add(markup);
                    //System.out.println(deep + "         deep");
                    //System.out.println(list + " list ");
                    //System.out.println("блять");
                }
            }
            //System.out.println(list + " " + deep);
            return list;
        }
        //System.out.println(list + " listik");
        return list;
    }
    private Markup choose(int next){
        if (next == 1) return new Strong(recurs(deep));
        if (next == 3) return new Italics(recurs(deep));
        if (next == 4) return new Underlined(recurs(deep));
        if (next == 2) return new Crossed(recurs(deep));
        return null;
    }
    private String getNextCommand() {
        isText = false;
        String command;
        //System.out.println(index + " index");
        if (index < input.length() - 2 && map.containsKey(input.substring(index, index + 3))){
            //System.out.println("Yes");
            index+=3;
            return map.get(input.substring(index - 3, index));
        }
        if (index < input.length() - 3 && map.containsKey(input.substring(index, index + 4))) {
            //System.out.println("ohhh YEs");
            index+=4;
            return map.get(input.substring(index - 4, index));
        }
        StringBuilder builder = new StringBuilder("");
        String next = "";
        while (index < input.length() && !(next = Character.toString(getNext())).equals("[")){
            builder.append(next);
        }
        if (next.equals("")) index++;
        if (next.equals("[")) index--;
        isText = true;
        return builder.toString();
    }

}