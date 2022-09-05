package markup;

import java.util.List;

public class Crossed extends Distributor {

    public Crossed(List<Markup> input) {
        super(input);
    }

    public void convert(StringBuilder builder) {
        mask(builder, "[s]", "[/s]");
    }

}