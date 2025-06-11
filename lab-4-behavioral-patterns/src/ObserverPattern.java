import java.util.*;

interface EventListener {
    void update(String eventType);
}

class HtmlElement {
    private String id;
    private Map<String, List<EventListener>> listeners = new HashMap<>();

    public HtmlElement(String id) {
        this.id = id;
    }

    public void addEventListener(String eventType, EventListener listener) {
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }

    public void trigger(String eventType) {
        System.out.println("Element [" + id + "] triggered event: " + eventType);
        if (listeners.containsKey(eventType)) {
            for (EventListener listener : listeners.get(eventType)) {
                listener.update(eventType);
            }
        }
    }
}

class ClickListener implements EventListener {
    public void update(String eventType) {
        System.out.println("ClickListener: Click event handled!");
    }
}

class MouseOverListener implements EventListener {
    public void update(String eventType) {
        System.out.println("MouseOverListener: Mouse over handled!");
    }
}

public class ObserverPattern {
    public static void run() {
        HtmlElement button = new HtmlElement("btnSubmit");

        button.addEventListener("click", new ClickListener());
        button.addEventListener("mouseover", new MouseOverListener());

        button.trigger("click");
        button.trigger("mouseover");
        button.trigger("keydown"); // no listener
    }
}

