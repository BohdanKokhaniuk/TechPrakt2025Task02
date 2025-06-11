import java.util.ArrayList;
import java.util.List;

abstract class LightNode {
    public abstract String innerHTML();
    public abstract String outerHTML();
}

class LightTextNode extends LightNode {
    private final String text;

    public LightTextNode(String text) {
        this.text = text;
    }

    public String innerHTML() { return text; }
    public String outerHTML() { return text; }
}

class LightElementNode extends LightNode {
    private final String tagName;
    private final List<LightNode> children = new ArrayList<>();

    public LightElementNode(String tag) {
        this.tagName = tag;
    }

    public void addChild(LightNode child) {
        children.add(child);
    }

    public String innerHTML() {
        StringBuilder sb = new StringBuilder();
        for (LightNode child : children) {
            sb.append(child.outerHTML());
        }
        return sb.toString();
    }

    public String outerHTML() {
        return "<" + tagName + ">" + innerHTML() + "</" + tagName + ">";
    }
}

class CompositePattern {
    public static void run() {
        LightElementNode div = new LightElementNode("div");
        div.addChild(new LightTextNode("Hello "));
        LightElementNode strong = new LightElementNode("strong");
        strong.addChild(new LightTextNode("World"));
        div.addChild(strong);
        System.out.println(div.outerHTML());
    }
}

