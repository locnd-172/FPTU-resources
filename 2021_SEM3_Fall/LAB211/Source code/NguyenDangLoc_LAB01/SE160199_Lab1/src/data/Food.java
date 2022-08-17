
package data;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Food {
    private String id;
    private String name;
    private double weight;
    private String type;
    private String place;
    private String expiredDate;

    public Food() {
        this.id = this.name = this.type = this.place = this.expiredDate = "";
        this.weight = 0;
    }
    
    public Food(String id, String name, double weight, String type, String place, String expiredDate) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.type = type;
        this.place = place;
        this.expiredDate = expiredDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    @Override
    public String toString() {
        return String.format("| %-8s| %-15s| %-12.2f| %-12s| %-8s| %-10s\n",
                            id, name, weight, type, place, expiredDate); 
    }
    
    public void showInfo() {
        System.out.printf("| %-8s| %-15s| %-12.2f| %-12s| %-8s| %-10s\n",
                            id, name, weight, type, place, expiredDate);
    
    }
    
    
}
