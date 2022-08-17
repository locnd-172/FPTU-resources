package fpgrowth;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */

class StringCompare implements Comparator<String> {
    public int compare(String o1, String o2) {
        if (o1.length() < o2.length()) return -1;
        else if (o1.length() > o2.length()) return 1;
        else {
            for (int i = 0; i < o1.length(); i++) {
                if (o1.charAt(i) > o2.charAt(i)) return 1;
                else if (o1.charAt(i) < o2.charAt(i)) return -1;
            }
            return 0;
        }
    }
}

public class Test {
    
    private long startTime;
    private long endTime;
    
    String fileName, path;
    StringBuilder sb; 

    StringCompare strCmp = new StringCompare();

    public Test(String fileName, String path) {
        this.fileName = fileName;
        this.path = path;
    }

    public Test() {
    }

    public void assertTwoFile() throws Exception {
        
//        CodeVSWeka("F:\\CSD\\ASS2\\COMPARE\\samp_weka.txt",  // weka output
//                   "F:\\CSD\\ASS2\\COMPARE\\samp_code.txt"); // code output
//        System.out.println("==============================");
        
        CodeVSTool("F:\\CSD\\ASS2\\COMPARE\\por3.txt",    // tool output
                   "F:\\CSD\\ASS2\\COMPARE\\por2.txt"); // code output
    }
    
    public void CodeVSWeka(String wekaRes, String codeRes) throws Exception {
        startTime = System.currentTimeMillis();
        
        
        String wekaOut = normalizeWekaOutput(wekaRes);
        String codeOut = normalizeCodeOutput(codeRes);
        
        compareLine(wekaOut, codeOut);
        
        endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("\nTime: " + duration + "ms");
    }
    
    public void CodeVSTool(String toolRes, String codeRes) throws Exception {
        startTime = System.currentTimeMillis();
        
        String toolOut = normalizeCodeOutput(toolRes);
        String codeOut = normalizeCodeOutput(codeRes);
        
        compareLine(toolOut, codeOut);
        
        endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("\nTime: " + duration + "ms");
    }
    
    public String getFilename(String s) {
        int it1 = 0, it2 = 0;
        for (int i = s.length() - 1; i >= 0; --i) {
            if (s.charAt(i)== '.') it2 = i;
            if (s.charAt(i) == '\\') {
                it1 = i + 1;
                break;
            }
        }
        path = s.substring(0, it1);
        return s.substring(it1, it2);
    }
    
    public String normalizeWekaOutput(String input) throws Exception {
        
        fileName = getFilename(input);
        System.out.println("File name: " + fileName);

        File f = new File(input);
        Scanner fi = new Scanner(f);

        ArrayList<String> list = new ArrayList<>();
        sb = new StringBuilder("");
        
        try {
            if (!f.exists()) {
                throw new Exception("The file does not exist! Try another file name...");
            }
            if (f.length() == 0) {
                throw new Exception("The file is empty! Add some data and try again...");
            }

            while (fi.hasNext()) {
                String line = fi.nextLine().trim().replaceAll("\\s{2,}", " ");
                if (line.isEmpty() || line.isBlank()) continue;
                
                String[] fp = line.split("\\s");
                if (fp[0].equals("Size") || fp[0].equals("Large")) continue;
                
                String sup = fp[fp.length - 1];
                String[] ext = Arrays.copyOf(fp, fp.length - 1);
                
                Arrays.sort(ext);
                
                StringBuilder cur = new  StringBuilder("");
                for (int i = 0; i < ext.length; i++) {
                    String[] items = ext[i].split("=");
                    cur.append(items[0]);
                    if (i != ext.length - 1) cur.append(" ");
                }
                cur.append(":").append(sup);
                
                list.add(cur.toString());
            }
            
            Collections.sort(list, strCmp);
            
            for (String el : list) 
                sb.append(el).append("\n");
            
            String wekaOut = path + fileName + "(n).txt";
            writeFile(wekaOut, sb);
            
            return wekaOut;
        } catch (IOException e) {
            return null;
        }
    }
    
    public String normalizeCodeOutput(String input) throws Exception {
        
        fileName = getFilename(input);
        System.out.println("File name: " + fileName);

        File f = new File(input);
        Scanner fi = new Scanner(f);

        ArrayList<String> list = new ArrayList<>();
        sb = new StringBuilder("");
        
        try {
            if (!f.exists()) {
                throw new Exception("The file does not exist! Try another file name...");
            }
            if (f.length() == 0) {
                throw new Exception("The file is empty! Add some data and try again...");
            }

            while (fi.hasNext()) {
                String line = fi.nextLine().trim().replaceAll("\\s{2,}", " ");
                
                if (line.isEmpty() || line.isBlank()) continue;
                
                String[] tmp = line.split(":");
                String[] fp = tmp[0].split("\\s");
                String last = tmp[1];
                
                Arrays.sort(fp);
                
                StringBuilder cur = new StringBuilder("");
                for (int i = 0; i < fp.length; i++) {
                    cur.append(fp[i]);
                    if (i != fp.length - 1) cur.append(" ");
                }
                cur.append(":").append(last);
                
                list.add(cur.toString());
            }
            
            Collections.sort(list, strCmp);
            for (String el : list) 
                sb.append(el).append("\n");
            
            String codeOut = path + fileName + "(n).txt";
            writeFile(codeOut, sb);
            return codeOut;
        } catch (IOException e) {
            return null;
        }
        
    }

    public void writeFile(String name, StringBuilder data) throws IOException {
        FileWriter pw = null;
        try {
            pw = new FileWriter(new File(name));
            pw.write(data.toString());
            pw.close();
            //System.out.println("WRITE FILE SUCCESSFUL!");
        } catch (FileNotFoundException e) {
            System.err.println("WRITE FILE FAILED");
        }
    }
    
    public void compareLine(String wekaOut, String codeOut) throws IOException {
        File file1 = new File(wekaOut);
        File file2 = new File(codeOut);
        
        double cmp = isEqual(file1.toPath(), file2.toPath());
        System.out.printf("Compare result: %.2f%%", cmp);
    }
    
    private double isEqual(Path file1, Path file2) {
        long cnt = 0;
        HashSet<String> set1 = new HashSet<>();
        HashSet<String> set2 = new HashSet<>();
        try {
            try (BufferedReader bf1 = Files.newBufferedReader(file1);
                 BufferedReader bf2 = Files.newBufferedReader(file2)) {
                String line;
                while ((line = bf1.readLine()) != null) set1.add(line);
                while ((line = bf2.readLine()) != null) set2.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Iterator value = set1.iterator();
        while (value.hasNext()) {
            String s = (String) value.next();
            if (set2.contains(s)) ++cnt;
            
        } 
        double res = ((double)cnt / (double)set1.size()) * 100;
        return res;
    }
}
