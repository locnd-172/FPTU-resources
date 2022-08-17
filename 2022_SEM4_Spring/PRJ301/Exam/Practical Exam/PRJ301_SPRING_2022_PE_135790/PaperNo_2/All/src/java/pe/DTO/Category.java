package pe.DTO;

/**
 *
 * @author Loc NgD <locndse160199@fpt.edu.vn>
 */
public class Category {

    private int CateID;
    private String CateName;

    public Category() {
    }

    public Category(int CateID, String CateName) {
        this.CateID = CateID;
        this.CateName = CateName;
    }

    public int getCateID() {
        return CateID;
    }

    public void setCateID(int CateID) {
        this.CateID = CateID;
    }

    public String getCateName() {
        return CateName;
    }

    public void setCateName(String CateName) {
        this.CateName = CateName;
    }

    @Override
    public String toString() {
        return "Categories{" + "CateID=" + CateID + ", CateName=" + CateName + '}';
    }

}
