package markup;

import java.util.List;

public class Paragraph extends Distributor {
    public Paragraph(List<Markup> input) {
        super(input);
    }

    public void toMarkdown(StringBuilder builder){
        mask(true, builder, "", "");
    }

    public void toHtml(StringBuilder builder) {
        mask(false, builder, "", "");
    }
}