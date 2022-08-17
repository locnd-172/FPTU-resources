package graph;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Edge {
    private int u, v, w;
    
    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
        this.w = 0;
    }

    public Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    public int getU() {
        return u;
    }

    public void setU(int u) {
        this.u = u;
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
        return "Edge{" + "u=" + u + ", v=" + v + ", w=" + w + '}';
    }
    
    
    
}
