


// It separates abstraction (Shape) from its implementation (Rendering Method) so that the two can evolve independently.
// In essence, the Bridge Pattern decouples what is being done (shapes) from how it is being done (rendering methods).


interface Renderer {
    public String renderCircle(int radius);
    public String renderRectangle(int side);
}

class VectorRenderer implements Renderer {
    @Override
    public String renderCircle(int radius) {
        return "Drawing Circle of radius " + radius + " as lines";
    }

    @Override
    public String renderRectangle(int side) {
        return "Drawing Rectangle of side " + side + " as lines";
    }
}

class RasterRenderer implements Renderer {
    @Override
    public String renderCircle(int radius) {
        return "Drawing Circle of radius " + radius + " as pixels";
    }
    @Override
    public String renderRectangle(int side) {
        return "Drawing Rectangle of side " + side + " as pixels";
    }
}
abstract class Shape {
    protected Renderer renderer;

    public Shape(Renderer renderer) {
        this.renderer = renderer;
    }

    public abstract String draw();
}

class Circle extends Shape {
    private int radius;

    public Circle(Renderer renderer, int radius) {
        super(renderer);
        this.radius = radius;
    }

    @Override
    public String draw() {
        return renderer.renderCircle(radius);
    }
}


public class Bridge {
    public static void main(String[] args) {
        Renderer vectorRenderer = new VectorRenderer();
        Shape circle = new Circle(vectorRenderer, 5);
        System.out.println(circle.draw());
    }
}