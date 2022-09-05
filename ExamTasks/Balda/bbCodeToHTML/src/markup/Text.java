package markup;

public class Text implements Markup {
    private final String words;

    public Text(String text) {
        words = text;
    }

    public void convert(StringBuilder builder) {
        builder.append(words);
    }
}