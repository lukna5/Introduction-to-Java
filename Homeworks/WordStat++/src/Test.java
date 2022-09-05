import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        int j = 0;
        Map<String, List<String>> map = new LinkedHashMap<>();
        map.put("1",List.of("a", "b", "c"));
        map.put("2",List.of("q", "w", "e"));
        Map<String, StringBuilder> map2 = new LinkedHashMap<>();
        StringBuilder builder = new StringBuilder();
        builder.append("aa");
        map2.put("1", builder);
        map2.put("1", map2.get("1").append("bb"));

        System.out.println(map2.get("1"));
        char c = 94;
        System.out.println(c);
        //System.out.println(map.entrySet());
        //System.out.println(map.get("1"));
        //map.put("1", map.get("1").add("f"));
        /*for (Map.Entry<String, List<String>> pair : map.entrySet()) {
            j++;
            System.out.println(j);
            int k = 0;
            for (int i = 0; i < pair.getValue().length(); i++){
                if (pair.getValue().charAt(i) == ':') {
                    k += 1;
                }
            }
            System.out.print(pair.getKey() + " " + k + pair.getValue() + System.lineSeparator());
        }

         */
    }
}
