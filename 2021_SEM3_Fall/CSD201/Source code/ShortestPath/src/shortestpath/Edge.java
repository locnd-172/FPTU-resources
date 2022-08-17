package shortestpath;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Edge implements Comparable<Edge> {
    public int u, v;
    public Integer w;
    
    public Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
    
    @Override
    public int compareTo(Edge o) {
        return this.w.compareTo(o.w);
    }

    
}
