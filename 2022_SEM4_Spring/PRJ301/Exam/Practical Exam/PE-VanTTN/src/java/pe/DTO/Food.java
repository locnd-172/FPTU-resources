/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.DTO;

/**
 *
 * @author Admin
 */
public class Food {

    private String foodID;
    private String foodName;
    private int cookingTime;
    private int status;

    public Food() {
    }

    public Food(String foodID, String foodName, int cookingTime, int status) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.cookingTime = cookingTime;
        this.status = status;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Food{" + "foodID=" + foodID + ", foodName=" + foodName + ", cookingTime=" + cookingTime + ", status=" + status + '}';
    }

}
