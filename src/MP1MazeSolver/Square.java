package MP1MazeSolver;

public class Square implements Comparable<Square>{
    private int x, y;
    private int g;
    private int h;
    private int fn;
    private Square parent;
    private String element;

    Square(String c, int x, int y){
        this.x = x;
        this.y = y;
        element = c;
        h = 0;
        g = 0;
        fn = 0;
        parent = null;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getFn() {
        return fn;
    }

    public void setFn(int fn) {
        this.fn = fn;
    }

    public Square getParent() {
        return parent;
    }

    public void setParent(Square parent) {
        this.parent = parent;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    boolean hasParent(){
        return parent != null;
    }

    @Override
    public int compareTo(Square o) {
        return Integer.compare(fn, o.fn);
    }
}
