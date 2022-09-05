package markup;

import java.util.List;

public abstract class Distributor implements Markup {
    List<Markup> input;

    public Distributor(List<Markup> input) {
        this.input = input;
    }

    public void mask( boolean isMd, StringBuilder builder, String left, String right) {
        builder.append(left);
        //System.out.println("left = " + left);
        //System.out.println(" builder = " + builder);
        //int i=0;
        //System.out.println("start = " + i);
        //System.out.println("content = " + input);
        for (int i = 0; i < input.size(); i++) {
            //i++;
            //System.out.println("i = " +i);
            if (isMd) {
                input.get(i).toMarkdown(builder);
            } else {
                input.get(i).toHtml(builder);
            }
        }
        builder.append(right);
    }
}