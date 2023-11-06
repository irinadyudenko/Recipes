import java.util.List;

class Sort {
    public static void sortShapes(Shape[] array,
                                  List<Shape> shapes,
                                  List<Polygon> polygons,
                                  List<Square> squares,
                                  List<Circle> circles) {
        // write your code here
        for(Shape shape : array) {
            switch (shape.getClass().getName()) {
                case "Shape" -> shapes.add(shape);
                case "Polygon" -> polygons.add((Polygon) shape);
                case "Square" -> squares.add((Square) shape);
                case "Circle" -> circles.add((Circle) shape);
            }
        }
    }
}

//Don't change classes below
class Shape { }
class Polygon extends Shape { }
class Square extends Polygon { }
class Circle extends Shape { }