import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

interface TextReader {
    char[][] read(String path);
}

class SmartTextReader implements TextReader {
    public char[][] read(String path) {
        ArrayList<char[]> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line.toCharArray());
            }
        } catch (IOException e) {
            System.out.println("Read error: " + e.getMessage());
        }
        return lines.toArray(new char[0][]);
    }
}

class SmartTextChecker implements TextReader {
    private final TextReader reader = new SmartTextReader();

    public char[][] read(String path) {
        System.out.println("Opening file...");
        char[][] data = reader.read(path);
        int chars = 0;
        for (char[] line : data) chars += line.length;
        System.out.println("Lines: " + data.length + ", Chars: " + chars);
        System.out.println("File closed.");
        return data;
    }
}

class SmartTextReaderLocker implements TextReader {
    private final String denyPattern;
    private final TextReader reader = new SmartTextReader();

    public SmartTextReaderLocker(String regex) {
        this.denyPattern = regex;
    }

    public char[][] read(String path) {
        if (path.matches(denyPattern)) {
            System.out.println("Access denied!");
            return new char[0][];
        } else {
            return reader.read(path);
        }
    }
}

class ProxyPattern {
    public static void run() {
        TextReader reader1 = new SmartTextChecker();
        reader1.read("sample.txt");

        TextReader reader2 = new SmartTextReaderLocker(".*secret.*");
        reader2.read("secret_file.txt");
    }
}
