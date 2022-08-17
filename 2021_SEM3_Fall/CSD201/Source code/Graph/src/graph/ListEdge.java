package graph;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class ListEdge {
    private int v, w;

    public ListEdge(int v, int w) {
        this.v = v;
        this.w = w;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    @Override
    public String toString() {
        return "LEdge{" + "v=" + v + ", w=" + w + '}';
    }
}
