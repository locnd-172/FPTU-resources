package hamilton;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
class ListEdge implements Comparable<ListEdge>  {
    public Integer v, w;

    public ListEdge(int v, int w) {
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(ListEdge o) {
        return this.w.compareTo(o.w);
    }
}
