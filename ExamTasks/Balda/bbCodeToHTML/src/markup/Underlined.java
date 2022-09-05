package markup;

import java.util.List;

public class Underlined extends Distributor {
    public Underlined(List<Markup> input) {
        super(input);
    }

    public void convert(StringBuilder builder) {
        mask(builder, "[u]", "[/u]");
    }
}