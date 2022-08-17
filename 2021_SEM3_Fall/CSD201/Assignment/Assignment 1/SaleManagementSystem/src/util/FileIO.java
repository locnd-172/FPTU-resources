package util;

import data.Customer;
import data.Product;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import singlelinkedlist.SLL;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class FileIO {
    
     public static SLL<Product> loadProduct(String fileName) throws Exception {
//        File f1 = new File(System.getProperty("user.dir"));
//        File f2 = f1.getParentFile();
//        File f = new File(f2.getParent() + "/" + fileName);
        
        File f = new File(fileName);
        FileReader fr = null;
        BufferedReader bf = null;
        
        try {
            if (!f.exists())
                throw new Exception("The file does not exist! Try another file name...");

            if (f.length() == 0)
                throw new Exception("The file is empty! Add some data and try again...");
            
            fr = new FileReader(f);
            bf = new BufferedReader(fr);
            SLL<Product> prodList = new SLL();
            while (bf.ready()) {
                String prodInfo = bf.readLine();
                prodInfo = prodInfo.replaceAll("\\s{1,}", "").trim();
                
                String[] iProInfo = prodInfo.split("\\|");
                
                String pcode = iProInfo[0].trim();
                String name = iProInfo[1].trim();
                int quantity = Integer.parseInt(iProInfo[2].trim());
                int sold = Integer.parseInt(iProInfo[3].trim());
                double price = Double.parseDouble(iProInfo[4].trim());
                
                prodList.addLast(new Product(pcode, name, quantity, sold, price));
            }
            fr.close(); bf.close();
            return prodList;
        } catch (IOException e) {
            return null;
        }
    }
     
    public static SLL<Customer> loadCustomer(String fileName) throws Exception {
//        File f1 = new File(System.getProperty("user.dir"));
//        File f2 = f1.getParentFile();
//        File f = new File(f2.getParent() + "/" + fileName);
        
        File f = new File(fileName);
        FileReader fr = null;
        BufferedReader bf = null;

        try {
            if (!f.exists())
                throw new Exception("The file does not exist! Try another file name...");

            if (f.length() == 0)
                throw new Exception("The file is empty! Add some data and try again...");
            
            fr = new FileReader(f);
            bf = new BufferedReader(fr);
            SLL<Customer> cusList = new SLL();
            while (bf.ready()) {
                String custInfo = bf.readLine();
                custInfo = custInfo.replaceAll("\\s{1,}", "").trim();
                
                String[] iCusInfo = custInfo.split("\\|");
                
                String ccode = iCusInfo[0].trim();
                String name = iCusInfo[1].trim();
                String phone = iCusInfo[2].trim();
                
                cusList.addLast(new Customer(ccode, name, phone));
            }
            fr.close(); bf.close();
            return cusList;
        } catch (IOException e) {
            return null;
        }
    }
    
    public static boolean saveProduct(String fileName, String header, SLL<Product> prodList) throws Exception {
        File f1 = new File(System.getProperty("user.dir"));
        File f2 = f1.getParentFile();
        File f = new File(f2.getParent() + "/" + fileName);
        
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(f);
            pw.print(header);
            String data;
            for (int i = 0; i < prodList.size(); i++) {
                data = String.format("%02d | ", i + 1);
                data += prodList.get(i).toString();
                pw.print(data);
            }
            pw.flush();
            pw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    public static boolean saveCustomer(String fileName, String header, SLL<Customer> cusList) throws Exception {
        File f1 = new File(System.getProperty("user.dir"));
        File f2 = f1.getParentFile();
        File f = new File(f2.getParent() + "/" + fileName);
        
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(f);
            pw.print(header);
            String data;
            for (int i = 0; i < cusList.size(); i++) {
                data = String.format("%02d | ", i + 1);
                data += cusList.get(i).toString();
                pw.print(data);
            }
            pw.flush();
            pw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
