package gentest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class GenTest {

    public static void main(String[] args) {
        int nItem = 50;                // number of itema
        int nTransaction = 30;         // number of transactions
        
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < nItem; ++i) 
            a.add(get(1000, 9999));
        
        System.out.println(a);
        
        StringBuilder sb = new StringBuilder("");
        StringBuilder sb2 = new StringBuilder("");
        
        for (int i = 0; i < nTransaction; ++i) {
            
            int sz = get(1, nItem); 
            ArrayList<Integer> trans = new ArrayList<>();
            boolean[] vis = new boolean[nItem];
            
            while (sz-- > 0) { 
                int id = get(0, nItem - 1);
                if (vis[id]) continue;
                vis[id] = true;
                trans.add(a.get(id));
            }
            
            for (Integer item : trans) {
                sb.append(i + 1).append(",").append(item).append("\n");
                sb2.append(item).append(" ");
            }
            sb2.append("\n");
        }
        save(sb, "D:\\FPGrowth\\FPGrowth\\test\\testcase\\test8", ".csv");
        save(sb2, "D:\\FPGrowth\\FPGrowth\\test\\testcase\\test8", ".txt");
        
    }

    static int get(int min, int max) {
        int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
        return random_int;
    }
    
    static void save(StringBuilder sb, String fileName, String ext) {
        String file = fileName + ext;
        File f = new File(file);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(f);
            pw.print(sb);
            pw.close();
        } catch (FileNotFoundException e) {
            
        }
    }
}
