package markup;

public class Text implements Markup {
    private final String words;

    public Text(String text) {
        words = text;
    }

    public void toMarkdown(StringBuilder builder) {
        builder.append(words);
    }
    public void toHtml(StringBuilder builder) {
        builder.append(words);
    }
}