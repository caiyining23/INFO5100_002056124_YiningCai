abstract class Shape {
    public static String color = "Blue";

    public abstract double calculateArea();
    public abstract double calculatePerimeter();

    public String getShapeName() {
        return "Unknown Shape";
    }
}

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


class Square extends Rectangle {

    public Square(double side) {
        super(side, side);
    }

    @Override
    public String getShapeName() {
        return "Square";
    }
}


public class Main {
    public static void main(String[] args) {
        Shape triangle = new Triangle(3, 4, 3, 4, 5);
        Shape rectangle = new Rectangle(5, 7);
        Shape circle = new Circle(6);
        Shape square = new Square(4);

        System.out.println(triangle.getShapeName() + " Area: " + triangle.calculateArea());
        System.out.println(triangle.getShapeName() + " Perimeter: " + triangle.calculatePerimeter());

        System.out.println(rectangle.getShapeName() + " Area: " + rectangle.calculateArea());
        System.out.println(rectangle.getShapeName() + " Perimeter: " + rectangle.calculatePerimeter());

        System.out.println(circle.getShapeName() + " Area: " + circle.calculateArea());
        System.out.println(circle.getShapeName() + " Perimeter: " + circle.calculatePerimeter());

        System.out.println(square.getShapeName() + " Area: " + square.calculateArea());
        System.out.println(square.getShapeName() + " Perimeter: " + square.calculatePerimeter());

        System.out.println("The color of all shapes is: " + Shape.color);
    }
}