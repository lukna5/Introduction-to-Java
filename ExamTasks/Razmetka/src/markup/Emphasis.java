package markup;

import java.util.List;

public class Emphasis extends Distributor {
    public Emphasis(List<Markup> input) {
        super(input);
    }

    public void toMarkdown(StringBuilder builder){
        mask(true, builder, "*", "*");
    }

    public void toHtml(StringBuilder builder) {
        mask(false, builder, "<em>", "</em>");
    }

}
