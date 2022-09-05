package markup;

import java.util.List;

public class Strong extends Distributor {

    public Strong(List<Markup> input) {
        super(input);
    }

    public void convert(StringBuilder builder) {
        mask(builder, "[b]", "[/b]");
    }

}