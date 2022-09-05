package markup;

import java.util.List;

public class Strikeout extends Distributor {

    public Strikeout(List<Markup> input) {
        super(input);
    }

    public void toMarkdown(StringBuilder builder){
        mask(true, builder, "~", "~");
    }


    public void toHtml(StringBuilder builder) {
        mask(false, builder, "<s>", "</s>");
    }

}