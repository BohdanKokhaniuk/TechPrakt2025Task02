class TextDocument {
    private String content = "";

    public void write(String text) {
        content += text;
    }

    public String read() {
        return content;
    }

    public Memento save() {
        return new Memento(content);
    }

    public void restore(Memento memento) {
        this.content = memento.getContent();
    }

    static class Memento {
        private final String content;

        private Memento(String content) {
            this.content = content;
        }

        private String getContent() {
            return content;
        }
    }
}

class TextEditor {
    private TextDocument document = new TextDocument();
    private TextDocument.Memento savedState;

    public void type(String text) {
        document.write(text);
    }

    public void save() {
        savedState = document.save();
        System.out.println("Document saved.");
    }

    public void undo() {
        if (savedState != null) {
            document.restore(savedState);
            System.out.println("Undo to previous state.");
        }
    }

    public void print() {
        System.out.println("Document: " + document.read());
    }
}

public class MementoPattern {
    public static void run() {
        TextEditor editor = new TextEditor();

        editor.type("Hello ");
        editor.save();

        editor.type("world!");
        editor.print(); // Hello world!

        editor.undo();
        editor.print(); // Hello
    }
}

