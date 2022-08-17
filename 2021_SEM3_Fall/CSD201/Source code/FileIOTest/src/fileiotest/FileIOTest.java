package fileiotest;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class FileIOTest {

    public static void main(String[] args) throws IOException {
        File f = new File("data.txt");
        
        // GET FILE ATTRIBUTES
        System.out.println("File name: " + f.getName());
        System.out.println("Abs file name: " + f.getAbsoluteFile());
        System.out.println("Abs path: " + f.getAbsolutePath());
        System.out.println("Correct path: " + f.getCanonicalPath());
        
        System.out.println("Last modified date: " + new Date(f.lastModified()));
        System.out.println("Hidden attribute: " + f.isHidden());
        System.out.println("can-read attribute: " + f.canRead());
        System.out.println("can-write attribute: " + f.canWrite());
        System.out.println("Size: " + f.length() + "bytes");
        
        System.out.println("____________________________________");
        // ACCESS A FOLDER 
        File f2 = new File("../CSD");
        
        String S = f2.isDirectory() ? "Folder" : "File";
        System.out.println("../CSD :" + S);
        String L[] = f2.list();
        System.out.println("Locate: ");
        for (int i = 0; i < L.length; ++i) {
            File ff2 = new File(f2, L[i]);
            System.out.println(L[i] + " " + (ff2.isFile()?"File":"Folder"));
            
        }
        
    }
    
}
