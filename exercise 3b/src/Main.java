import java.io.*;

// Make Shape serializable
abstract class Shape implements Serializable {
    public static String color = "Blue";

    public abstract double calculateArea();
    public abstract double calculatePerimeter();

    public String getShapeName() {
        return "Unknown Shape";
    }
}

// Triangle class implementing Serializable
class Triangle extends Shape {
    private double base;
    private double height;
    private double sideA, sideB, sideC;

    public Triangle(double base, double height, double sideA, double sideB, double sideC) {
        this.base = base;
        this.height = height;
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    @Override
    public double calculateArea() {
        return (base * height) / 2;
    }

    @Override
    public double calculatePerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public String getShapeName() {
        return "Triangle";
    }
}

// Rectangle class implementing Serializable
class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (length + width);
    }

    @Override
    public String getShapeName() {
        return "Rectangle";
    }
}

// Circle class implementing Serializable
class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String getShapeName() {
        return "Circle";
    }
}

// Square class implementing Serializable
class Square extends Rectangle {
    public Square(double side) {
        super(side, side);
    }

    @Override
    public String getShapeName() {
        return "Square";
    }
}

// Main class for testing serialization
public class Main {
    public static void main(String[] args) {
        // Create shape objects
        Shape triangle = new Triangle(3, 4, 3, 4, 5);
        Shape rectangle = new Rectangle(5, 7);
        Shape circle = new Circle(6);
        Shape square = new Square(4);

        // Serialize objects
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("shapes.ser"))) {
            out.writeObject(triangle);
            out.writeObject(rectangle);
            out.writeObject(circle);
            out.writeObject(square);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize objects
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("shapes.ser"))) {
            Shape deserializedTriangle = (Shape) in.readObject();
            Shape deserializedRectangle = (Shape) in.readObject();
            Shape deserializedCircle = (Shape) in.readObject();
            Shape deserializedSquare = (Shape) in.readObject();

            // Display deserialized objects
            System.out.println(deserializedTriangle.getShapeName() + " Area: " + deserializedTriangle.calculateArea());
            System.out.println(deserializedTriangle.getShapeName() + " Perimeter: " + deserializedTriangle.calculatePerimeter());

            System.out.println(deserializedRectangle.getShapeName() + " Area: " + deserializedRectangle.calculateArea());
            System.out.println(deserializedRectangle.getShapeName() + " Perimeter: " + deserializedRectangle.calculatePerimeter());

            System.out.println(deserializedCircle.getShapeName() + " Area: " + deserializedCircle.calculateArea());
            System.out.println(deserializedCircle.getShapeName() + " Perimeter: " + deserializedCircle.calculatePerimeter());

            System.out.println(deserializedSquare.getShapeName() + " Area: " + deserializedSquare.calculateArea());
            System.out.println(deserializedSquare.getShapeName() + " Perimeter: " + deserializedSquare.calculatePerimeter());

            System.out.println("The color of all shapes is: " + Shape.color);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
