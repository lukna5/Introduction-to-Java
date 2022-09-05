package md2html;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Md2Html {
    public static void main(String[] args) {
        if (args.length > 2) {
            System.out.println("Invalid Input.");
            return;
        }

        ArrayList<String> strs = new ArrayList<>();

        try (BufferedReader bf = new BufferedReader(new FileReader(args[0], StandardCharsets.UTF_8))) {
            while (true) {
                String str = bf.readLine();
                if (str != null) {
                    strs.add(str);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        List<String> paragraphs = list(strs);
        List<String> results = new ArrayList<>();

        for (String paragraph : paragraphs) {
            results.add(transformParagraph(paragraph));
        }

        try (PrintWriter pw = new PrintWriter(args[1], StandardCharsets.UTF_8)) {
            for (String result : results) {
                pw.println(result);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<String> list(List<String> strs) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (String str : strs) {
            if (!str.equals("")) {
                if (sb.length() == 0) {
                    sb.append(str);
                } else {
                    sb.append(System.lineSeparator()).append(str);
                }
            } else {
                if (sb.length() > 0) {
                    result.add(sb.toString());
                    sb = new StringBuilder();
                }
            }
        }
        if (sb.length() > 0) {
            result.add(sb.toString());
        }

        return result;
    }

    public static String transformParagraph(String str) {
        int pos = 0;
        char c = str.charAt(pos);
        while (c == '#') {
            pos++;
            if (pos < str.length()) {
                c = str.charAt(pos);
            } else {
                break;
            }
        }

        int level;
        if (Character.isWhitespace(c)) {
            level = pos;
        } else {
            level = 0;
        }

        String result;
        if (level == 0) {
            result = "<p>" + innerProc(str) + "</p>";
        } else {
            result = "<h" + level + ">" + innerProc(str.substring(pos + 1)) + "</h" + level + ">";
        }

        return result;
    }

    public static String innerProc(String str) {
        HashMap<Integer, Replace> replace = new HashMap<>();
        HashMap<String, Integer> symb = new HashMap<>();
        List<String> marks = List.of("**", "__", "*","_", "--", "`", "~");
        Map<String, String> htmlMark = Map.of(
                "**", "strong",
                "__", "strong",
                "*", "em",
                "_","em",
                "--", "s",
                "`", "code",
                "~", "mark"
        );
        Map<String, String> aloneMark = Map.of(
                "<", "&lt;",
                ">", "&gt;",
                "&", "&amp;"
        );
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            if (str.startsWith("\\", i)) {
                replace.put(i, new Replace(i, i, ""));
                i += 1;
                continue;
            }

            for (String mark : aloneMark.keySet()) {
                if (str.startsWith(mark, i)) {
                        replace.put(i, new Replace(i, i, aloneMark.get(mark)));
                        break;
                    }
                }

            for (String mark : marks) {
                if (str.startsWith(mark, i)) {
                    if (symb.containsKey(mark)) {
                        int ind = symb.get(mark);
                        replace.put(ind, new Replace(ind, ind + mark.length() - 1, "<" + htmlMark.get(mark) + ">"));
                        replace.put(i, new Replace(i, i + mark.length() - 1, "</" + htmlMark.get(mark) + ">"));
                        symb.remove(mark);
                    } else {
                        symb.put(mark, i);
                    }
                    break;
                }
            }
        }

        for (int i = 0; i < str.length(); i++) {
            if (replace.containsKey(i)) {
                sb.append(replace.get(i).symb);
                i = replace.get(i).end;
            } else {
                sb.append(str.charAt(i));
            }
        }

        return sb.toString();
    }

    static class Replace {
        int begin, end;
        String symb;

        public Replace (int begin, int end, String symb) {
            this.begin = begin;
            this.end = end;
            this.symb = symb;
        }
    }
}
