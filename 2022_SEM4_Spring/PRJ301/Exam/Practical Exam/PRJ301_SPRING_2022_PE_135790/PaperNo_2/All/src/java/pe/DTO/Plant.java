package pe.DTO;

/**
 *
 * @author Loc NgD <locndse160199@fpt.edu.vn>
 */
public class Plant {

    private int PID;
    private String PName;
    private int price;
    private String imgPath;
    private String description;
    private int status;
    private int CateID;
    private String CateName;

    public Plant() {
    }

    public Plant(int PID, String PName, int price, String imgPath, String description, int status, int CateID, String CateName) {
        this.PID = PID;
        this.PName = PName;
        this.price = price;
        this.imgPath = imgPath;
        this.description = description;
        this.status = status;
        this.CateID = CateID;
        this.CateName = CateName;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

}
