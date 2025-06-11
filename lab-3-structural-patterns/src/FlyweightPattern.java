import java.util.HashMap;
import java.util.Map;

class TagFlyweight {
    private final String tagName;

    public TagFlyweight(String tag) {
        this.tagName = tag;
    }

    public String wrap(String content) {
        return "<" + tagName + ">" + content + "</" + tagName + ">";
    }
}

class TagFactory {
    private static final Map<String, TagFlyweight> tags = new HashMap<>();

    public static TagFlyweight get(String tagName) {
        return tags.computeIfAbsent(tagName, TagFlyweight::new);
    }

    public static int count() {
        return tags.size();
    }
}

class FlyweightPattern {
    public static void run() {
        String[] lines = {
            "Hamlet",
            "Act 1",
            "  To be, or not to be.",
            "Something else..."
        };

        StringBuilder page = new StringBuilder();

        for (String line : lines) {
            TagFlyweight tag;
            if (line.equals(lines[0])) tag = TagFactory.get("h1");
            else if (line.length() < 20) tag = TagFactory.get("h2");
            else if (line.startsWith(" ")) tag = TagFactory.get("blockquote");
            else tag = TagFactory.get("p");

            page.append(tag.wrap(line)).append("\n");
        }

        System.out.println(page);
        System.out.println("Unique tags used: " + TagFactory.count());
    }
}

