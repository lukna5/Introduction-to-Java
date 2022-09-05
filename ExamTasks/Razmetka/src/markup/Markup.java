package markup;

public interface Markup {
    void toMarkdown(StringBuilder builder);
    void toHtml(StringBuilder builder);
}