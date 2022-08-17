package pe.Utils;

/**
 *
 * @author Admin
 */
public class FoodError {

    private String foodIDError;
    private String foodNameError;
    private String cTimeError;
    private String statusError;

    public FoodError() {
        this.foodIDError = "";
        this.foodNameError = "";
        this.cTimeError = "";
        this.statusError = "";
    }

    public FoodError(String foodIDError, String foodNameError, String cTimeError, String statusError) {
        this.foodIDError = foodIDError;
        this.foodNameError = foodNameError;
        this.cTimeError = cTimeError;
        this.statusError = statusError;
    }

    public String getFoodIDError() {
        return foodIDError;
    }

    public void setFoodIDError(String foodIDError) {
        this.foodIDError = foodIDError;
    }

    public String getFoodNameError() {
        return foodNameError;
    }

    public void setFoodNameError(String foodNameError) {
        this.foodNameError = foodNameError;
    }

    public String getcTimeError() {
        return cTimeError;
    }

    public void setcTimeError(String cTimeError) {
        this.cTimeError = cTimeError;
    }

    public String getStatusError() {
        return statusError;
    }

    public void setStatusError(String statusError) {
        this.statusError = statusError;
    }

    @Override
    public String toString() {
        return "FoodError{" + "foodIDError=" + foodIDError + ", foodNameError=" + foodNameError + ", cTimeError=" + cTimeError + ", statusError=" + statusError + '}';
    }

}
