package fpgrowth;

public class FPGrowth {

    static String fileName, path;
    
    static String out;
    
    public static void main(String[] args) throws Exception {
        
        convertInpFiles();
        runFPGrowth();
        compare();
        
    }
    
    public static void convertInpFiles() throws Exception {
        String inp = "F:\\CSD\\ASS2\\TEST\\Online Retail (France).csv";
        TransactionEncode en = new TransactionEncode(inp);
        en.encode();
        path = en.path;
        fileName = en.getFilename(en.input);
        System.out.println("______________________________");
    }
    
    public static void runFPGrowth() {
        FPGrowthLib lib = new FPGrowthLib();
        String inp = path + fileName + ".txt";
        out = path + fileName + ".out";
        lib.execution(inp, out, 0.05);
        System.out.println("______________________________");
        
    }
    
    public static void compare() throws Exception {
        System.out.println("COMPARE 2 FILES");
        Test t = new Test();
        String toolRes = path + "france.txt";
        t.CodeVSTool(toolRes, out);
    }
}
