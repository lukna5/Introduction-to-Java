package markup;

import java.util.List;

public class Italics extends Distributor { // курсив
    public Italics(List<Markup> input) {
        super(input);
    }

    public void convert(StringBuilder builder) {
        mask(builder, "[i]", "[/i]>");
    }

}
