interface Renderer {
    void render(String shape);
}

class VectorRenderer implements Renderer {
    public void render(String shape) {
        System.out.println("Drawing " + shape + " as vectors.");
    }
}

class RasterRenderer implements Renderer {
    public void render(String shape) {
        System.out.println("Drawing " + shape + " as pixels.");
    }
}

abstract class Shape {
    protected Renderer renderer;

    public Shape(Renderer renderer) {
        this.renderer = renderer;
    }

    public abstract void draw();
}

class Circle extends Shape {
    public Circle(Renderer r) { super(r); }
    public void draw() { renderer.render("Circle"); }
}

class Triangle extends Shape {
    public Triangle(Renderer r) { super(r); }
    public void draw() { renderer.render("Triangle"); }
}

class BridgePattern {
    public static void run() {
        Shape c = new Circle(new VectorRenderer());
        Shape t = new Triangle(new RasterRenderer());
        c.draw();
        t.draw();
    }
}
