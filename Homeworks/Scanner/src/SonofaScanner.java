import java.io.*;
import java.nio.charset.StandardCharsets;

class SonofaScanner {

    private final BufferedReader reader;
    private InputStream input;
    private StringBuilder packetline = null;
    private StringBuilder packetnext = new StringBuilder();
    private Integer packetint = null;
    private int lengthline = 0;


    public SonofaScanner(InputStream input) {

        this.input = input;
        reader = new BufferedReader(new InputStreamReader(input));
    }

    public SonofaScanner (String string) {

        reader = new BufferedReader(new StringReader(string));
    }

    public SonofaScanner(File file, String string) throws FileNotFoundException, UnsupportedEncodingException {
        this.input = new FileInputStream (file);
        reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
    }

    public SonofaScanner(File file) throws FileNotFoundException {

        this.input = new FileInputStream (file);
        reader = new BufferedReader(new InputStreamReader(input));
    }


    // обработка и возврат строки

    public boolean hasNextLine() throws IOException {
        if (packetline != null) return true;
        else {
            int tempint;
            if ((tempint = reader.read()) != -1) { // проверяет наличие строк для чтения
                packetline = new StringBuilder();
                while ((tempint != -1) && ((char) tempint != '\n')) {
                    packetline.append((char) tempint);
                    tempint = reader.read();
                }
                return true;
            } else {
                return false;
            }
        }
    }
    public boolean hasNextInt() throws IOException {
        if (packetnext.length() != 0) {
            try { // проверяет, является ли следующий next числом
                packetint = Integer.parseInt(packetnext.toString());
            } catch (Exception e) {
                packetnext = new StringBuilder();
                return hasNextInt();
            }
            return true;
        } else {
            if (hasNext()) {
                return hasNextInt();
            } else {
                return false;
            }
        }
    }
    public boolean hasNext() throws IOException {
        if (packetnext.length() != 0) {
            return true;
        } else {
            if (packetline == null) {
                if ((hasNextLine())) { // заполнение буффера Line
                    return hasNext();
                } else {
                    return false;
                }
            } else{
                if (lengthline == packetline.length()) {
                    lengthline = 0;
                    packetline = null;
                    hasNextLine();
                    return hasNext();
                }
                if (Character.isWhitespace(packetline.charAt(lengthline))) {
                    lengthline++;
                    return hasNext();
                } else {
                    while ((lengthline != packetline.length()) && (!Character.isWhitespace(packetline.charAt(lengthline)))) { // заполнение буффера для next
                        packetnext.append(packetline.charAt(lengthline));
                        lengthline++;
                    }
                    boolean have = packetnext.length() == 0;
                    return !have;
                }

            }
        }
    }

    public String nextLine() throws IOException {

        if (packetline != null) { // выводит буффер (при его наличии)
            String timely1 = packetline.toString();
            packetline = null;
            return timely1;
        } else {
            if (hasNextLine()) { // заполняет буффер и вывидит его
                String timely2 = packetline.toString();
                packetline = null;
                return timely2;
            } else {
                return null;
            }
        }
    }
    public String next() throws IOException {
        if (packetnext.length() != 0) {
            String timely = packetnext.toString();
            packetnext = new StringBuilder();
            return timely;
        } else {
            if (hasNext()) {
                return next();
            } else {
                return null;
            }
        }
    }
    public Integer nextInt() throws IOException {
        if (packetint != null) {
            packetnext = new StringBuilder();
            Integer timely = packetint;
            packetint = null;
            return timely;
        } else {
            if (hasNextInt()) {
                return nextInt();
            } else {
                return null;
            }
        }
    }

    public void close()  throws IOException {
        reader.close();
    }
}
