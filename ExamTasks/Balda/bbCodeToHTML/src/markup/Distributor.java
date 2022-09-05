package markup;

import java.util.List;

public abstract class Distributor implements Markup {
    List<Markup> input;

    public Distributor(List<Markup> input) {
        this.input = input;
    }

    public void mask(StringBuilder builder, String left, String right) {
        builder.append(left);
        for (int i = 0; i < input.size(); i++) {
            input.get(i).convert(builder);
        }
        builder.append(right);
    }
}