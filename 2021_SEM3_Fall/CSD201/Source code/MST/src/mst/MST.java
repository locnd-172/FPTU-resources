// kruskal

package mst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */

class edge implements Comparable<edge>{
    int u, v;
    Long w;

    public edge(int u, int v, Long w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(edge other) {
        return this.w.compareTo(other.w);
    }
}

public class MST {
    static Scanner sc = new Scanner(System.in);
    static int[] par, hei;
    static int cntNode = 0;
    static ArrayList<edge> edgeList; 
    
    public static void main(String[] args) {
        int n, m;

        edgeList = new ArrayList<>();

        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < m; ++i) {
            int u, v;
            long w;
            u = sc.nextInt(); u--;
            v = sc.nextInt(); v--;
            w = sc.nextLong();
            edgeList.add(new edge(u, v, w));
        }
        findMST(n);
    }
    public static void findMST(int n) {
        par = new int[n];
        hei = new int[n];
        for (int i = 0; i < n; ++i) par[i] = i;
        Arrays.fill(hei, 0);
        
        Collections.sort(edgeList);
        
        long res = 0;
        for (int i = 0; i < edgeList.size(); ++i) {
            int u = edgeList.get(i).u;
            int v = edgeList.get(i).v;
            if (find(u) != find(v)) {
                merge(u, v);
                res += edgeList.get(i).w;
            }
        }
        System.out.println(res);
        
    }
    
    static int find(int u) {
        if (par[u] == u) return u;
        else return (par[u] = find(par[u]));
    }
    
    static void merge(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) return;

        if (hei[u] == hei[v]) {
            hei[u]++;
            par[v] = u;
        }
        if (hei[u] > hei[v])
            par[v] = u;
        else
            par[u] = v;
    }
}