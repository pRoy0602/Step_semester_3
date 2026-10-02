public class Q1 {
    static abstract class Shape {
        private static int counter=1000;
        private final String shapeId;
        Shape(){shapeId="S-"+(++counter);}
        public abstract double calculateArea();
        void scale(double factor){scaleOne(factor);}
        void scale(double xFactor,double yFactor){scaleOne(xFactor);scaleOne(yFactor);}
        protected abstract void scaleOne(double factor);
        String getShapeId(){return shapeId;}
    }
    static class CircleShape extends Shape {
        private double radius;
        CircleShape(double radius){if(radius<=0)throw new IllegalArgumentException();this.radius=radius;}
        public double calculateArea(){return Math.PI*radius*radius;}
        protected void scaleOne(double factor){if(factor<=0)throw new IllegalArgumentException();radius*=factor;}
    }
    static class SquareShape extends Shape {
        private double side;
        SquareShape(double side){if(side<=0)throw new IllegalArgumentException();this.side=side;}
        public double calculateArea(){return side*side;}
        protected void scaleOne(double factor){if(factor<=0)throw new IllegalArgumentException();side*=factor;}
    }
    static void printArea(Shape s){System.out.println(s.calculateArea());}
    public static void main(String[] args){
        CircleShape c=new CircleShape(5.0);
        SquareShape sq=new SquareShape(4.0);
        printArea(c);
        printArea(sq);
        sq.scale(2.0);
        printArea(sq);
        System.out.println(c.getShapeId());
    }
    // new Shape() is impossible because Shape is abstract.
}
