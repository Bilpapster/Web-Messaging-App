package Templates.RMI.Squares;

public class Rectangle implements java.io.Serializable {
    public int length;
    public int height;

    public Rectangle(int length, int height) {
        this.length = length;
        this.height = height;
    }
}
