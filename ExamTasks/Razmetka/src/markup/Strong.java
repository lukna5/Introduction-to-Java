package markup;

import java.util.List;

public class Strong extends Distributor {

    public Strong(List<Markup> input) {
        super(input);
    }

    public void toMarkdown(StringBuilder builder) {
        mask(true, builder, "__", "__");
    }

    public void toHtml(StringBuilder builder) {
        mask(false, builder, "<strong>", "</strong>");
    }

}