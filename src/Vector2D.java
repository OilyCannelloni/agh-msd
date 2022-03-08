import java.util.LinkedList;

public class Vector2D {
    public int x, y;
    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D mod(int n, int k) {
        return new Vector2D(Math.floorMod(x, n), Math.floorMod(y, k));
    }

    public Vector2D add(Vector2D v) {
        return new Vector2D(x + v.x, y + v.y);
    }

    private LinkedList<Vector2D> getDirectionVectors() {
        LinkedList<Vector2D> dirs = new LinkedList<>();
        dirs.add(new Vector2D(-1, -1));
        dirs.add(new Vector2D(0, -1));
        dirs.add(new Vector2D(1, -1));
        dirs.add(new Vector2D(1, 0));
        dirs.add(new Vector2D(1, 1));
        dirs.add(new Vector2D(0, 1));
        dirs.add(new Vector2D(-1, 1));
        dirs.add(new Vector2D(-1, 0));
        return dirs;
    }

    public LinkedList<Vector2D> getNeighbours(int width, int height) {
        LinkedList<Vector2D> neighbours = new LinkedList<>();
        for (Vector2D d : this.getDirectionVectors()) {
            neighbours.add(this.add(d).mod(width, height));
        }
        return neighbours;
    }

    public String toString() {
        return String.format("(%d, %d)", this.x, this.y);
    }
}
